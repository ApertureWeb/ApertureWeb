package com.aperture.community.message.component.nacos.client.naming.backups;

import com.aperture.community.message.component.nacos.api.exception.NacosException;
import com.aperture.community.message.component.nacos.api.naming.pojo.ServiceInfo;
import com.aperture.community.message.component.nacos.client.naming.cache.ConcurrentDiskUtil;
import com.aperture.community.message.component.nacos.client.naming.cache.DiskCache;
import com.aperture.community.message.component.nacos.client.naming.core.HostReactor;
import com.aperture.community.message.component.nacos.client.naming.utils.CollectionUtils;
import com.aperture.community.message.component.nacos.client.naming.utils.UtilAndComs;
import com.aperture.community.message.component.nacos.common.utils.JacksonUtils;
import com.aperture.community.message.component.nacos.common.utils.StringUtils;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author HALOXIAO
 * @since 2020-10-28 15:58
 **/
public class FailoverReactor implements Closeable {

    private final String failoverDir;
    private final Logger logger = LoggerFactory.getLogger(FailoverReactor.class);
    private final HostReactor hostReactor;
    private final Vertx vertx;
    private final WorkerExecutor executor;
    private Map<String, ServiceInfo> serviceMap = new ConcurrentHashMap<String, ServiceInfo>();

    /**
     * 用于判断是否处于故障切换状态
     */
    private final Map<String, String> switchParams = new ConcurrentHashMap<String, String>();

    private static final long DAY_PERIOD_MINUTES = 24 * 60;


    public FailoverReactor(HostReactor hostReactor, String cacheDir, Vertx vertx, WorkerExecutor executor) {
        this.hostReactor = hostReactor;
        this.failoverDir = cacheDir + "/failover";
        this.vertx = vertx;
        this.executor = executor;
        this.init();
    }


    /**
     * Init.
     */
    public void init() {
        //5s执行一次
        vertx.setPeriodic(5000, handler -> {
            new SwitchRefresher().run();
        });
        executorService.scheduleWithFixedDelay(new SwitchRefresher(), 0L, 5000L, TimeUnit.MILLISECONDS);
        //24h执行一次
        executorService.scheduleWithFixedDelay(new DiskFileWriter(), 30, DAY_PERIOD_MINUTES, TimeUnit.MINUTES);

        // backup file on startup if failover directory is empty.
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    File cacheDir = new File(failoverDir);
                    //如果备份存放目录无法创建
                    if (!cacheDir.exists() && !cacheDir.mkdirs()) {
                        throw new IllegalStateException("failed to create cache dir: " + failoverDir);
                    }

                    File[] files = cacheDir.listFiles();
                    //如果备份存放目录是空的，就先把内存中的服务信息持久化到磁盘先
                    if (files == null || files.length <= 0) {
                        //将内存中的服务实例(HostReactor存储的)缓存持久化到硬盘中
                        new DiskFileWriter().run();
                    }
                } catch (Throwable e) {
                    logger.error("[NA] failed to backup file on startup.", e);
                }

            }
        }, 10000L, TimeUnit.MILLISECONDS);
    }

    class SwitchRefresher {
        long lastModifiedMillis = 0L;

        //定期做容灾备份，查看缓存目录下的/failover/00-00---000-VIPSRV_FAILOVER_SWITCH-000---00-00文件，看是否有改变，
        //有改变就要设置相应的属性来表示是否开启故障转移模式
        public void run() {
            //是否有故障转移文件
            FileSystem file = vertx.fileSystem();
            String filePath = failoverDir + UtilAndComs.FAILOVER_SWITCH;
            file.exists(filePath, r -> {
                if ((r.succeeded() && !r.result()) || r.failed()) {
                    switchParams.put("failover-mode", "false");
                    logger.debug("failover switch is not found, " + filePath);
                    return;
                }
            }).props(filePath, r -> {
                long modified = 0L;
                if (r.succeeded()) {
                    modified = r.result().lastModifiedTime();
                }
                if (lastModifiedMillis < modified) {
                    lastModifiedMillis = modified;
                    //获取文件内容
                    ConcurrentDiskUtil.getFileContent(executor, filePath, Charset.defaultCharset().toString()).onSuccess(failover -> {
                        if (!StringUtils.isEmpty(failover)) {
                            String[] lines = failover.split(DiskCache.getLineSeparator());
                            for (String line : lines) {
                                String line1 = line.trim();
                                if ("1".equals(line1)) {
                                    switchParams.put("failover-mode", "true");
                                    logger.info("failover-mode is on");
                                    //将磁盘中的备份文件读取到内存中
                                    new FailoverFileReader().run();
                                } else if ("0".equals(line1)) {
                                    switchParams.put("failover-mode", "false");
                                    logger.info("failover-mode is off");
                                }
                            }
                        } else {
                            switchParams.put("failover-mode", "false");
                        }
                    }).onFailure(err -> {
                        logger.error("[NA] failed to read failover switch.", err);
                    });

                }
            });


        }
    }

    class FailoverFileReader {
        //读取磁盘中服务的备份信息，并将其写入内存的缓存中
        public void run() {
            //暂存读取的资料
            Map<String, ServiceInfo> domMap = new HashMap<String, ServiceInfo>(16);

            BufferedReader reader = null;
            try {
                FileSystem fileSystem = vertx.fileSystem();
                Future<Boolean> exFuture = fileSystem.exists(failoverDir);
                exFuture.compose(
                        res -> {
                            Future<Void> mkFuture = Future.succeededFuture();
                            if (!res) {
                                mkFuture = fileSystem.mkdirs(failoverDir);
                            }
                            return mkFuture;
                        }).onFailure(err -> {
                    logger.error("failed to create cache dir: " + failoverDir);
                }).compose(res -> fileSystem.readDir(failoverDir
                )).compose(files -> {
                    if (files == null) {
                        return Future.failedFuture("no file in:" + failoverDir);
                    }
                    OpenOptions options = new OpenOptions();
                    options.setWrite(false);
                    options.setCreate(false);
                    for (String file : files) {
                        fileSystem.props(file)
                                .compose(fProps -> {
                                    if (!fProps.isRegularFile()) {
                                        return Future.failedFuture("continue");
                                    }
                                    //TODO last modify
//                                    if()
                                });
                    }
                });

                File cacheDir = new File(failoverDir);

                //如果缓存目录不存在且创建失败
                if (!cacheDir.exists() && !cacheDir.mkdirs()) {
                    throw new IllegalStateException("failed to create cache dir: " + failoverDir);
                }
                File[] files = cacheDir.listFiles();
                if (files == null) {
                    return;
                }

                for (File file : files) {

                    if (!file.isFile()) {
                        continue;
                    }
                    //如果是00-00---000-VIPSRV_FAILOVER_SWITCH-000---00-00这项文件
                    if (file.getName().equals(UtilAndComs.FAILOVER_SWITCH)) {
                        continue;
                    }

                    ServiceInfo dom = new ServiceInfo(file.getName());

                    try {
                        //获取磁盘中存放的服务实例数据
                        String dataString = ConcurrentDiskUtil.getFileContent(file, Charset.defaultCharset().toString());
                        reader = new BufferedReader(new StringReader(dataString));

                        String json;
                        if ((json = reader.readLine()) != null) {
                            try {
                                dom = JacksonUtils.toObj(json, ServiceInfo.class);
                            } catch (Exception e) {
                                logger.error("[NA] error while parsing cached dom : " + json, e);
                            }
                        }

                    } catch (Exception e) {
                        logger.error("[NA] failed to read cache for dom: " + file.getName(), e);
                    } finally {
                        try {
                            if (reader != null) {
                                reader.close();
                            }
                        } catch (Exception e) {
                            //ignore
                        }
                    }
                    if (!CollectionUtils.isEmpty(dom.getHosts())) {
                        domMap.put(dom.getKey(), dom);
                    }
                }
            } catch (
                    Exception e) {
                logger.error("[NA] failed to read cache file", e);
            }

            if (domMap.size() > 0) {
                //正式放到内存中
                serviceMap = domMap;
            }
        }
    }

    //定时将内存中的服务实例(HostReactor存储的)缓存持久化到硬盘中
    class DiskFileWriter extends TimerTask {
        @Override
        public void run() {
            //获取内存中的服务实例缓存
            Map<String, ServiceInfo> map = hostReactor.getServiceInfoMap();
            for (Map.Entry<String, ServiceInfo> entry : map.entrySet()) {
                ServiceInfo serviceInfo = entry.getValue();
                if (StringUtils.equals(serviceInfo.getKey(), UtilAndComs.ALL_IPS) || StringUtils
                        .equals(serviceInfo.getName(), UtilAndComs.ENV_LIST_KEY) || StringUtils
                        .equals(serviceInfo.getName(), "00-00---000-ENV_CONFIGS-000---00-00") || StringUtils
                        .equals(serviceInfo.getName(), "vipclient.properties") || StringUtils
                        .equals(serviceInfo.getName(), "00-00---000-ALL_HOSTS-000---00-00")) {
                    continue;
                }

                DiskCache.write(serviceInfo, failoverDir);
            }
        }
    }

    public boolean isFailoverSwitch() {
        return Boolean.parseBoolean(switchParams.get("failover-mode"));
    }

    public ServiceInfo getService(String key) {
        ServiceInfo serviceInfo = serviceMap.get(key);

        if (serviceInfo == null) {
            serviceInfo = new ServiceInfo();
            serviceInfo.setName(key);
        }

        return serviceInfo;
    }


    @Override
    public void close() throws IOException {

    }
}