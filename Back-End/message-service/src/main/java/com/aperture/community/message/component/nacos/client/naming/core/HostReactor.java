package com.aperture.community.message.component.nacos.client.naming.core;

import com.aperture.community.message.component.nacos.api.NacosWorkerExecutorFactory;
import com.aperture.community.message.component.nacos.api.common.Constants;
import com.aperture.community.message.component.nacos.api.exception.NacosException;
import com.aperture.community.message.component.nacos.api.naming.pojo.Instance;
import com.aperture.community.message.component.nacos.api.naming.pojo.ServiceInfo;
import com.aperture.community.message.component.nacos.client.naming.backups.FailoverReactor;
import com.aperture.community.message.component.nacos.client.naming.beat.BeatInfo;
import com.aperture.community.message.component.nacos.client.naming.beat.BeatReactor;
import com.aperture.community.message.component.nacos.client.naming.cache.ConcurrentDiskUtil;
import com.aperture.community.message.component.nacos.client.naming.cache.DiskCache;
import com.aperture.community.message.component.nacos.client.naming.net.NamingProxy;
import com.aperture.community.message.component.nacos.client.naming.utils.CollectionUtils;
import com.aperture.community.message.component.nacos.common.utils.IoUtils;
import com.aperture.community.message.component.nacos.common.utils.JacksonUtils;
import com.aperture.community.message.component.nacos.common.utils.OsUtils;
import com.aperture.community.message.component.nacos.common.utils.StringUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
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
//TODO 需要大改内核
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

    /**
     * 存放已被更新过的服务
     */
    private final Map<String, ScheduledFuture<?>> futureMap = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(HostReactor.class);


    //内存的服务实例缓存
    private Map<String, ServiceInfo> serviceInfoMap;

    /**
     * it used to  mark  service that updating
     */
    private final Map<String, Object> updatingMap;

    private final PushReceiver pushReceiver;

    private final EventDispatcher eventDispatcher;

    private final BeatReactor beatReactor;

    private final NamingProxy serverProxy;

    private final FailoverReactor failoverReactor;

    private final String cacheDir;
    private final Vertx vertx;
    private final WorkerExecutor executor;


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
        this.executor = NacosWorkerExecutorFactory.getWorkExecutor();

        //是否从磁盘读取缓存,默认为false
        if (loadCacheAtStart) {
            vertx.deployVerticle(new InitServiceInfoTask()).onFailure(res ->
                    this.serviceInfoMap = new ConcurrentHashMap<>(16));
        } else {
            this.serviceInfoMap = new ConcurrentHashMap<String, ServiceInfo>(16);
        }

        this.updatingMap = new ConcurrentHashMap<>();
        this.failoverReactor = new FailoverReactor(this, cacheDir, vertx, executor);
        this.pushReceiver = new PushReceiver(this);
    }


    public Map<String, ServiceInfo> getServiceInfoMap() {
        return serviceInfoMap;
    }

    private ServiceInfo getServiceInfo0(String serviceName, String clusters) {
        String key = ServiceInfo.getKey(serviceName, clusters);
        return serviceInfoMap.get(key);
    }


    /**
     * 根据服务名称和群组获取ServiceInfo
     */
    public ServiceInfo getServiceInfo(final String serviceName, final String clusters) {
        logger.debug("failover-mode: " + failoverReactor.isFailoverSwitch());
        String key = ServiceInfo.getKey(serviceName, clusters);
        //是否开启了容灾备份
        if (failoverReactor.isFailoverSwitch()) {
            //返回容灾备份中的内存数据
            return failoverReactor.getService(key);
        }
        //在本地内存中获取
        ServiceInfo serviceObj = getServiceInfo0(serviceName, clusters);
        if (null == serviceObj) {
            serviceObj = new ServiceInfo(serviceName, clusters);
            serviceInfoMap.put(serviceObj.getKey(), serviceObj);
            updatingMap.put(serviceName, new Object());
            //立刻更新服务缓存
            updateServiceNow(serviceName, clusters);
            updatingMap.remove(serviceName);
        } else if (updatingMap.containsKey(serviceName)) {
            if (UPDATE_HOLD_INTERVAL > 0) {
                // hold a moment waiting for update finish
                // TODO used unblock way to complete it
                synchronized (serviceObj) {
                    try {
                        serviceObj.wait(UPDATE_HOLD_INTERVAL);
                    } catch (InterruptedException e) {
                        logger.error("[getServiceInfo] serviceName:" + serviceName + ", clusters:" + clusters, e);
                    }
                }
            }
        }
        //如果futureMap中不存在目标服务实例，则将其放定时服务中获取服务实例
        scheduleUpdateIfAbsent(serviceName, clusters);
        return serviceInfoMap.get(serviceObj.getKey());

    }

    public Future<ServiceInfo> getServiceInfoDirectlyFromServer(final String serviceName, final String clusters)
            throws NacosException {
        return serverProxy.queryList(serviceName, clusters, 0, false).compose(res -> {
            if (StringUtils.isNotEmpty(res)) {
                return Future.succeededFuture(JacksonUtils.toObj(res, ServiceInfo.class));
            }
            return Future.succeededFuture();
        });

    }


    /**
     * Schedule update if absent.
     *
     * @param serviceName service name
     * @param clusters    clusters
     */
    //看谁会调用这个
    public void scheduleUpdateIfAbsent(String serviceName, String clusters) {
        //双检
        if (futureMap.get(ServiceInfo.getKey(serviceName, clusters)) != null) {
            return;
        }
        synchronized (futureMap) {
            if (futureMap.get(ServiceInfo.getKey(serviceName, clusters)) != null) {
                return;
            }
            //TODO updated task once times
            futureMap.put(ServiceInfo.getKey(serviceName, clusters), future);
        }

    }

    /**
     * 更新服务实例
     * <p>
     * param:ServiceInfo 服务信息
     */
    public ServiceInfo processServiceJson(String json) {
        //新的服务信息
        ServiceInfo serviceInfo = JacksonUtils.toObj(json, ServiceInfo.class);
        //从内存获取旧的服务
        ServiceInfo oldService = serviceInfoMap.get(serviceInfo.getKey());
        if (serviceInfo.getHosts() == null || !serviceInfo.validate()) {
            //empty or error push, just ignore
            return oldService;
        }

        boolean changed = false;

        if (oldService != null) {
            //LastRefTime是Unix时间戳
            if (oldService.getLastRefTime() > serviceInfo.getLastRefTime()) {
                logger.warn("out of date data received, old-t: " + oldService.getLastRefTime() + ", new-t: "
                        + serviceInfo.getLastRefTime());
            }
            serviceInfoMap.put(serviceInfo.getKey(), serviceInfo);
            //旧的服务地址
            Map<String, Instance> oldHostMap = new HashMap<String, Instance>(oldService.getHosts().size());
            for (Instance host : oldService.getHosts()) {
                //inetAddr()为：ip+":"+port 的形式
                oldHostMap.put(host.toInetAddr(), host);
            }
            //新的服务地址
            Map<String, Instance> newHostMap = new HashMap<String, Instance>(serviceInfo.getHosts().size());
            for (Instance host : serviceInfo.getHosts()) {
                newHostMap.put(host.toInetAddr(), host);
            }
            //存放修改过的服务
            Set<Instance> modHosts = new HashSet<Instance>();
            //存放全新的服务
            Set<Instance> newHosts = new HashSet<Instance>();
            //存放被移除的服务
            Set<Instance> remvHosts = new HashSet<Instance>();
            List<Map.Entry<String, Instance>> newServiceHosts = new ArrayList<Map.Entry<String, Instance>>(
                    newHostMap.entrySet());
            //对新的服务地址做处理
            for (Map.Entry<String, Instance> entry : newServiceHosts) {
                Instance host = entry.getValue();
                String key = entry.getKey();
                //当某个实例也在oldHostMap中存在，且更新过（和oldHostMap中的数据不同）时，加入modHosts
                if (oldHostMap.containsKey(key) && !StringUtils.equals(host.toString(), oldHostMap.get(key).toString())) {
                    modHosts.add(host);
                    continue;
                }
                //添加newHosts
                if (!oldHostMap.containsKey(key)) {
                    newHosts.add(host);
                }
            }

            for (Map.Entry<String, Instance> entry : oldHostMap.entrySet()) {
                Instance host = entry.getValue();
                String key = entry.getKey();
                if (newHostMap.containsKey(key)) {
                    continue;
                }
                //添加remvHosts
                if (!newHostMap.containsKey(key)) {
                    remvHosts.add(host);
                }
            }

            if (newHosts.size() > 0) {
                changed = true;
                logger.info("new ips(" + newHosts.size() + ") service: " + serviceInfo.getKey() + " -> "
                        + JacksonUtils.toJson(newHosts));
            }

            if (remvHosts.size() > 0) {
                changed = true;
                logger.info("removed ips(" + remvHosts.size() + ") service: " + serviceInfo.getKey() + " -> "
                        + JacksonUtils.toJson(remvHosts));
            }

            if (modHosts.size() > 0) {
                changed = true;
                updateBeatInfo(modHosts);
                logger.info("modified ips(" + modHosts.size() + ") service: " + serviceInfo.getKey() + " -> "
                        + JacksonUtils.toJson(modHosts));
            }

            serviceInfo.setJsonFromServer(json);

            if (newHosts.size() > 0 || remvHosts.size() > 0 || modHosts.size() > 0) {
                //放入事件分发器当中
                eventDispatcher.serviceChanged(serviceInfo);
                //写入磁盘缓存
                DiskCache.write(serviceInfo, cacheDir, vertx, executor);
            }

        } else {
            changed = true;
            logger.info("init new ips(" + serviceInfo.ipCount() + ") service: " + serviceInfo.getKey() + " -> "
                    + JacksonUtils.toJson(serviceInfo.getHosts()));
            serviceInfoMap.put(serviceInfo.getKey(), serviceInfo);
            eventDispatcher.serviceChanged(serviceInfo);
            serviceInfo.setJsonFromServer(json);
            DiskCache.write(serviceInfo, cacheDir, vertx, executor);
        }
        //监控
//        MetricsMonitor.getServiceInfoMapSizeMonitor().set(serviceInfoMap.size());

        if (changed) {
            logger.info("current ips:(" + serviceInfo.ipCount() + ") service: " + serviceInfo.getKey() + " -> "
                    + JacksonUtils.toJson(serviceInfo.getHosts()));
        }

        return serviceInfo;
    }


    private void updateBeatInfo(Set<Instance> modHosts) {
        for (Instance instance : modHosts) {
            String key = beatReactor.buildKey(instance.getServiceName(), instance.getIp(), instance.getPort());
            //如果确实存在此实例，且为临时实例
            if (beatReactor.dom2Beat.containsKey(key) && instance.isEphemeral()) {
                BeatInfo beatInfo = beatReactor.buildBeatInfo(instance);
                //因为临时实例超时是会自动销毁的，所以需要Beat（前面进行的操作如果费时，那么这一系列临时实例容易被销毁）
                beatReactor.addBeatInfo(instance.getServiceName(), beatInfo);
            }
        }
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
        //获取服务的信息，并且发送自己接收服务端push的udp端口
        try {
            serverProxy.queryList(serviceName, clusters, pushReceiver.getUdpPort(), false)
                    .onComplete(res -> {
                        //refreshOnly不存在这个方法调用
                        if (res.succeeded()) {
                            if (StringUtils.isNotEmpty(res.result())) {
                                processServiceJson(res.result());
                            }
                        } else {
                            logger.error("[NA] failed to update serviceName: " + serviceName, res.cause());
                        }
                        if (oldService != null) {
                            synchronized (oldService) {
                                // maybe delete it?
                                oldService.notifyAll();
                            }
                        }

                    });
        } catch (NacosException e) {
            logger.error("[NA] failed to update serviceName: " + serviceName, e);
        }
    }

    /**
     * update serviceInfoMap on time
     */
    public class UpdateTask {
        long lastRefTime = Long.MAX_VALUE;

        private final String clusters;

        private final String serviceName;

        public UpdateTask(String serviceName, String clusters) {
            this.serviceName = serviceName;
            this.clusters = clusters;
        }

        public void  run() {
            long delayTime = -1;
            //获取本地缓存的目标服务的所有实例信息
            ServiceInfo serviceObj = serviceInfoMap.get(ServiceInfo.getKey(serviceName, clusters));
            //如果目标服务不存在
            if (serviceObj == null) {
                //获取服务实例实例信息,更新缓存
                updateServiceNow(serviceName, clusters);
                delayTime = DEFAULT_DELAY;
                return null;
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
            lastRefTime = serviceObj.getLastRefTime();
            //
            if (!eventDispatcher.isSubscribed(serviceName, clusters) && !futureMap
                    .containsKey(ServiceInfo.getKey(serviceName, clusters))) {
                // abort the update task
                logger.info("update task is stopped, service:" + serviceName + ", clusters:" + clusters);
            }
            //服务控制幅度
            // TODO check
            delayTime = serviceObj.getCacheMillis();
            vertx.setTimer(delayTime, s -> {
                new UpdateTask().run();
            });

        }

        /**
         * Refresh only.
         *
         * @param serviceName service name
         * @param clusters    cluster
         */
        public void refreshOnly(String serviceName, String clusters) {
            try {
                serverProxy.queryList(serviceName, clusters, pushReceiver.getUdpPort(), false);
            } catch (Exception e) {
                logger.error("[NA] failed to update serviceName: " + serviceName, e);
            }
        }

    }

    /**
     * init ServiceInfo from disk
     */
    public class InitServiceInfoTask extends AbstractVerticle {
        private final Map<String, ServiceInfo> domMap = new HashMap<>(16);
        private final String ADDRESS = "Init-Service-Info-Task";

        //TODO  check rewrite
        @Override
        public void start() throws Exception {
            FileSystem fileSystem = vertx.fileSystem();
            DiskCache.makeSureCacheDirExists(vertx.fileSystem(), cacheDir);
            //start
            fileSystem.readDir(cacheDir).onFailure(er -> {
                logger.error("can not read dir ");
            }).compose(files -> {
                if (files == null || files.size() == 0) {
                    serviceInfoMap = domMap;
                    return Future.failedFuture("");
                }
                return Future.succeededFuture(files);
            }).compose(files -> {
                OpenOptions openOptions = new OpenOptions();
                openOptions.setRead(true);
                openOptions.setWrite(false);
                //filePath:absolute path
                for (String filePath : files) {
                    fileSystem.lprops(filePath).compose(file -> {
                                if (!file.isRegularFile()) {
                                    //replace continue
                                    return Future.failedFuture("continue");
                                }
                                String fileName = URLDecoder.decode(filePath, StandardCharsets.UTF_8);
                                //获取文件名
                                fileName = fileName.substring(fileName.lastIndexOf(OsUtils.getFileSeparator()) + 1);
                                if ((fileName.endsWith(Constants.SERVICE_INFO_SPLITER + "meta") || fileName
                                        .endsWith(Constants.SERVICE_INFO_SPLITER + "special-url"))) {
                                    return Future.failedFuture("file name wrong");
                                }
                                ServiceInfo dom = new ServiceInfo(fileName);
                                List<Instance> ips = new ArrayList<>();
                                dom.setHosts(ips);
                                ConcurrentDiskUtil.getFileContent(executor, filePath).onSuccess(dataString -> {

                                    ServiceInfo newFormat = null;
                                    //TODO blocking，optimize it with nonblock way

                                /*
                                    IoUtils.readLines(dataString).compose(data -> {
                                        ServiceInfo newFormat2 = null;
                                        for (String j : data) {

                                            if (!j.startsWith("{")) {
                                                continue;
                                            }
                                            newFormat2 = JacksonUtils.toObj(j, ServiceInfo.class);
                                            if (StringUtils.isEmpty(newFormat2.getName())) {
                                                ips.add(JacksonUtils.toObj(j, Instance.class));
                                            }
                                        }

                                    });
                                 */


                                    // perfect ↑
                                    try (BufferedReader reader = new BufferedReader(new StringReader(dataString))) {
                                        String json;
                                        while ((json = reader.readLine()) != null) {
                                            if (!json.startsWith("{")) {
                                                continue;
                                            }
                                            newFormat = JacksonUtils.toObj(json, ServiceInfo.class);
                                            if (StringUtils.isEmpty(newFormat.getName())) {
                                                ips.add(JacksonUtils.toObj(json, Instance.class));
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
                                return Future.succeededFuture();
                            }
                    ).onFailure(
                            err -> {
                                logger.error("[NA] failed to read cache file", err.getCause());
                            }
                    );

                }

                serviceInfoMap = domMap;
                return Future.succeededFuture();
            }).onFailure(err -> logger.error("InitServiceInfoTask", err));

        }


    }


}
