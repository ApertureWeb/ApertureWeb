package com.aperture.community.message.component.nacos.client.naming.core;

import com.aperture.community.message.component.nacos.api.common.Constants;
import com.aperture.community.message.component.nacos.api.naming.pojo.Instance;
import com.aperture.community.message.component.nacos.api.naming.pojo.ServiceInfo;
import com.aperture.community.message.component.nacos.api.utils.StringUtils;
import com.aperture.community.message.component.nacos.client.naming.backups.FailoverReactor;
import com.aperture.community.message.component.nacos.client.naming.beat.BeatReactor;
import com.aperture.community.message.component.nacos.client.naming.cache.ConcurrentDiskUtil;
import com.aperture.community.message.component.nacos.client.naming.cache.DiskCache;
import com.aperture.community.message.component.nacos.client.naming.net.NamingProxy;
import com.aperture.community.message.component.nacos.client.naming.utils.CollectionUtils;
import com.aperture.community.message.component.nacos.common.utils.JacksonUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.StringReader;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @author HALOXIAO
 * @since 2020-10-25 19:34
 **/
@Slf4j
public class HostReactor {
    /**
     * 更新延迟
     */
    private static final long DEFAULT_DELAY = 1000L;

    /**
     * 更新间隔
     */
    private static final long UPDATE_HOLD_INTERVAL = 5000L;

    private final Map<String, ScheduledFuture<?>> futureMap = new HashMap<String, ScheduledFuture<?>>();
    private static Logger logger = LoggerFactory.getLogger(HostReactor.class);


    //内存的服务实例缓存
    private Map<String, ServiceInfo> serviceInfoMap;

    private final Map<String, Object> updatingMap;

    private final PushReceiver pushReceiver;

    private final EventDispatcher eventDispatcher;

    private final BeatReactor beatReactor;

    private final NamingProxy serverProxy;

    private final FailoverReactor failoverReactor;

    private final String cacheDir;
    private final Vertx vertx;


    //TODO init it
    private WorkerExecutor executor;

    public HostReactor(EventDispatcher eventDispatcher, NamingProxy serverProxy, BeatReactor beatReactor,
                       String cacheDir, Vertx vertx) {
        this(eventDispatcher, serverProxy, beatReactor, cacheDir, false, vertx);
    }

    public HostReactor(EventDispatcher eventDispatcher, NamingProxy serverProxy, BeatReactor beatReactor,
                       String cacheDir, boolean loadCacheAtStart, Vertx vertx) {
        this.vertx = vertx;
        this.eventDispatcher = eventDispatcher;
        this.beatReactor = beatReactor;
        this.serverProxy = serverProxy;
        this.cacheDir = cacheDir;

        //是否从磁盘读取缓存,默认为false
        if (loadCacheAtStart) {
            vertx.deployVerticle(new InitServiceInfoTask()).onFailure(res -> {
                this.serviceInfoMap = new ConcurrentHashMap<>(16);
            });
        } else {
            this.serviceInfoMap = new ConcurrentHashMap<String, ServiceInfo>(16);
        }

        this.updatingMap = new ConcurrentHashMap<String, Object>();
        this.failoverReactor = new FailoverReactor(this, cacheDir);
        this.pushReceiver = new PushReceiver();
    }


    public Map<String, ServiceInfo> getServiceInfoMap() {
        return serviceInfoMap;
    }

    private ServiceInfo getServiceInfo0(String serviceName, String clusters) {

        String key = ServiceInfo.getKey(serviceName, clusters);

        return serviceInfoMap.get(key);
    }


    /**
     * Update service now.
     *
     * @param serviceName service name
     * @param clusters    clusters
     */
    //更新服务实例
    public void updateServiceNow(String serviceName, String clusters) {
        ServiceInfo oldService = getServiceInfo0(serviceName, clusters);
        try {
            //获取服务的信息，并且发送自己接收服务端push的udp端口
            String result = serverProxy.queryList(serviceName, clusters, pushReceiver.getUdpPort(), false);

            //refreshOnly不存在这个方法调用
            if (StringUtils.isNotEmpty(result)) {
                processServiceJson(result);
            }
        } catch (Exception e) {
            NAMING_LOGGER.error("[NA] failed to update serviceName: " + serviceName, e);
        } finally {
            if (oldService != null) {
                synchronized (oldService) {
                    oldService.notifyAll();
                }
            }
        }
    }


    /**
     * update serviceInfoMap on time
     */
    public class UpdateTask extends AbstractVerticle {
        long lastRefTime = Long.MAX_VALUE;

        private final String clusters;

        private final String serviceName;

        public UpdateTask(String serviceName, String clusters) {
            this.serviceName = serviceName;
            this.clusters = clusters;
        }

        @Override
        public void start() throws Exception {
            long delayTime = -1;
            try {

                //获取本地缓存的目标服务的所有实例信息
                ServiceInfo serviceObj = serviceInfoMap.get(ServiceInfo.getKey(serviceName, clusters));
                //如果目标服务不存在
                if (serviceObj == null) {
                    //获取服务实例实例信息,更新缓存
                    updateServiceNow(serviceName, clusters);
                    delayTime = DEFAULT_DELAY;
                    return;
                }
                //判断是否被server推送了信息
                if (serviceObj.getLastRefTime() <= lastRefTime) {
                    updateServiceNow(serviceName, clusters);
                    serviceObj = serviceInfoMap.get(ServiceInfo.getKey(serviceName, clusters));
                } else {
                    // if serviceName already updated by push, we should not override it
                    // since the push data may be different from pull through force push
                    //如果服务名已经被推送了更新，我们就不应该覆盖它，因为强制推送的信息可能和拉取的信息不同
                    refreshOnly(serviceName, clusters);
                }


            } catch (Exception e) {

            }

        }

    }

    /**
     * init ServiceInfo from disk
     */
    public class InitServiceInfoTask extends AbstractVerticle {
        private final Map<String, ServiceInfo> domMap = new HashMap<>(16);
        private final String ADDRESS = "Init-Service-Info-Task";

        @Override
        public void start() throws Exception {
            FileSystem fileSystem = vertx.fileSystem();
            DiskCache.makeSureCacheDirExists(vertx.fileSystem(), cacheDir);
            List<String> files = fileSystem.readDir(cacheDir).onFailure(er -> {
                logger.error("can not read dir ");
            }).result();
            if (files == null || files.size() == 0) {
                serviceInfoMap = domMap;
                return;
            }
            OpenOptions openOptions = new OpenOptions();
            openOptions.setRead(true);
            openOptions.setWrite(false);
            for (String filePath : files) {
                fileSystem.lprops(filePath).onSuccess(file -> {
                            if (!file.isRegularFile()) {//replace continue

                            } else {
                                String fileName = URLDecoder.decode(filePath, StandardCharsets.UTF_8);
                                if (fileName.endsWith(Constants.SERVICE_INFO_SPLITER + "meta") || fileName
                                        .endsWith(Constants.SERVICE_INFO_SPLITER + "special-url")) {
                                    ServiceInfo dom = new ServiceInfo(fileName);
                                    List<Instance> ips = new ArrayList<>();
                                    dom.setHosts(ips);


                                    vertx.eventBus().consumer(ADDRESS).handler(msg -> {
                                        ServiceInfo newFormat = null;
                                        Objects.requireNonNull(msg.body());
                                        String dataString = msg.body().toString();
                                        try (BufferedReader reader = new BufferedReader(new StringReader(dataString))) {
                                            String json;
                                            //TODO blocking，optimize it
                                            while ((json = reader.readLine()) != null) {
                                                try {
                                                    if (!json.startsWith("{")) {
                                                        continue;
                                                    }
                                                    newFormat = JacksonUtils.toObj(json, ServiceInfo.class);
                                                    if (StringUtils.isEmpty(newFormat.getName())) {
                                                        ips.add(JacksonUtils.toObj(json, Instance.class));
                                                    }
                                                } catch (Throwable e) {
                                                    logger.error("[NA] error while parsing cache file: " + json, e);
                                                }
                                            }
                                        } catch (Exception e) {
                                            logger.error("[NA] failed to read cache for dom: " + filePath, e);
                                        }
                                        if (newFormat != null && !StringUtils.isEmpty(newFormat.getName()) && !CollectionUtils
                                                .isEmpty(newFormat.getHosts())) {
                                            domMap.put(dom.getKey(), newFormat);
                                        } else if (!CollectionUtils.isEmpty(dom.getHosts())) {
                                            domMap.put(dom.getKey(), dom);
                                        }

                                    });
                                    ConcurrentDiskUtil.getFileContent(executor, vertx, ADDRESS, cacheDir, Charset.defaultCharset().toString());

                                }
                            }
                        }
                ).onFailure(
                        err -> {
                            logger.error("[NA] failed to read cache file", err.getCause());
                        }
                );


            }
            serviceInfoMap = domMap;

        }


    }


}