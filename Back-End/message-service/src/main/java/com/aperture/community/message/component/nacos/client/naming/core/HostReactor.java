package com.aperture.community.message.component.nacos.client.naming.core;

import com.aperture.community.message.component.nacos.api.naming.pojo.ServiceInfo;
import com.aperture.community.message.component.nacos.client.naming.backups.FailoverReactor;
import com.aperture.community.message.component.nacos.client.naming.beat.BeatReactor;
import com.aperture.community.message.component.nacos.client.naming.cache.DiskCache;
import com.aperture.community.message.component.nacos.client.naming.net.NamingProxy;
import com.aperture.community.message.component.nacos.client.naming.utils.UtilAndComs;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author HALOXIAO
 * @since 2020-10-25 19:34
 **/
@Slf4j
public class HostReactor {
    //默认更新延迟
    private static final long DEFAULT_DELAY = 1000L;

    private static final long UPDATE_HOLD_INTERVAL = 5000L;

    private final Map<String, ScheduledFuture<?>> futureMap = new HashMap<String, ScheduledFuture<?>>();

    //内存的服务实例缓存
    private final Map<String, ServiceInfo> serviceInfoMap;

    private final Map<String, Object> updatingMap;

    private final PushReceiver pushReceiver;

    private final EventDispatcher eventDispatcher;

    private final BeatReactor beatReactor;

    private final NamingProxy serverProxy;

    private final FailoverReactor failoverReactor;

    private final String cacheDir;
    private final Vertx vertx;

    public HostReactor(EventDispatcher eventDispatcher, NamingProxy serverProxy, BeatReactor beatReactor,
                       String cacheDir, Vertx vertx) {
        this(eventDispatcher, serverProxy, beatReactor, cacheDir, false, UtilAndComs.DEFAULT_POLLING_THREAD_COUNT, vertx);
    }

    public HostReactor(EventDispatcher eventDispatcher, NamingProxy serverProxy, BeatReactor beatReactor,
                       String cacheDir, boolean loadCacheAtStart, int pollingThreadCount, Vertx vertx) {
        this.vertx = vertx;
        this.eventDispatcher = eventDispatcher;
        this.beatReactor = beatReactor;
        this.serverProxy = serverProxy;
        this.cacheDir = cacheDir;

        //是否从磁盘读取缓存,默认为false
        if (loadCacheAtStart) {
//            this.serviceInfoMap = new ConcurrentHashMap<String, ServiceInfo>(DiskCache.read(this.cacheDir));
            this.serviceInfoMap = new ConcurrentHashMap<>(16);
        } else {
            this.serviceInfoMap = new ConcurrentHashMap<String, ServiceInfo>(16);
        }

        this.updatingMap = new ConcurrentHashMap<String, Object>();
        this.failoverReactor = new FailoverReactor(this, cacheDir);
        this.pushReceiver = new PushReceiver();
    }


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


        }

    }


}
