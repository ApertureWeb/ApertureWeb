package com.aperture.community.message.component.nacos.client.naming.core;

import com.aperture.community.message.component.nacos.api.naming.listener.EventListener;
import com.aperture.community.message.component.nacos.api.naming.listener.NamingEvent;
import com.aperture.community.message.component.nacos.api.naming.pojo.Instance;
import com.aperture.community.message.component.nacos.api.naming.pojo.ServiceInfo;
import com.aperture.community.message.component.nacos.client.naming.utils.CollectionUtils;
import io.vertx.core.AbstractVerticle;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author HALOXIAO
 * @since 2020-10-27 16:37
 **/
public class EventDispatcher implements Closeable {


    private ExecutorService executor = null;

    private final LinkedList<ServiceInfo> serviceInfoQueue = new LinkedList<>();
    private final ConcurrentMap<String, List<EventListener>> observerMap = new ConcurrentHashMap<String, List<EventListener>>();

    private volatile boolean closed = false;

    public EventDispatcher() {
        this.executor = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "com.alibaba.nacos.naming.client.listener");
                thread.setDaemon(true);
                return thread;
            }
        });

        this.executor.execute(new Notifier());
    }


    /**
     * Service changed.
     *
     * @param serviceInfo service info
     */
    public void serviceChanged(ServiceInfo serviceInfo) {
        if (serviceInfo == null) {
            return;
        }

        changedServices.add(serviceInfo);
    }


    @Override
    public void close() throws IOException {

    }


    private class Notify extends AbstractVerticle {
        @Override
        public void start() throws Exception {
            vertx.setPeriodic(1000, msg -> {
                ServiceInfo serviceInfo = null;
                serviceInfo = serviceInfoQueue.getLast();
                if (serviceInfo == null) {// continue
                } else {
                    List<EventListener> listeners = observerMap.get(serviceInfo.getKey());
                    if (!CollectionUtils.isEmpty(listeners)) {
                        for (EventListener listener : listeners){
                            List<Instance> hosts = Collections.unmodifiableList(serviceInfo.getHosts());
                            listener.onEvent(new NamingEvent(serviceInfo.getName(),serviceInfo.getGroupName(),serviceInfo.getClusters(),hosts));
                        }
                    }
                }
            });
        }

    }
}
