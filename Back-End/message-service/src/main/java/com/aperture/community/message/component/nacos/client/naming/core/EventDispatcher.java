package com.aperture.community.message.component.nacos.client.naming.core;

import com.aperture.community.message.component.nacos.api.naming.listener.EventListener;
import com.aperture.community.message.component.nacos.api.naming.listener.NamingEvent;
import com.aperture.community.message.component.nacos.api.naming.pojo.Instance;
import com.aperture.community.message.component.nacos.api.naming.pojo.ServiceInfo;
import com.aperture.community.message.component.nacos.client.naming.utils.CollectionUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;

/**
 * @author HALOXIAO
 * @since 2020-10-27 16:37
 **/
public class EventDispatcher implements Closeable {


    private ExecutorService executor = null;

    private Future<String> notifyId;
    private final static Logger logger = LoggerFactory.getLogger(EventDispatcher.class);

    //ChangedServices,FIFO
    private final LinkedList<ServiceInfo> serviceInfoQueue = new LinkedList<>();
    private final ConcurrentMap<String, List<EventListener>> observerMap = new ConcurrentHashMap<>();
    private static final long MINUTES = 1000 * 60;
    private volatile boolean closed = false;
    private final Vertx vertx;

    public EventDispatcher(Vertx vertx) {
        this.vertx = vertx;
        //执行监听任务
        notifyId = vertx.deployVerticle(new Notify());

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
        serviceInfoQueue.addFirst(serviceInfo);

    }



    /**
     * Remove listener.
     *
     * @param serviceName service name
     * @param clusters    clusters
     * @param listener    listener
     */
    public void removeListener(String serviceName, String clusters, EventListener listener) {

        logger.info("[LISTENER] removing " + serviceName + " with " + clusters + " from listener map");

        List<EventListener> observers = observerMap.get(ServiceInfo.getKey(serviceName, clusters));
        if (observers != null) {
            Iterator<EventListener> iter = observers.iterator();
            while (iter.hasNext()) {
                EventListener oldListener = iter.next();
                if (oldListener.equals(listener)) {
                    iter.remove();
                }
            }
            if (observers.isEmpty()) {
                observerMap.remove(ServiceInfo.getKey(serviceName, clusters));
            }
        }
    }

    public boolean isSubscribed(String serviceName, String clusters) {
        return observerMap.containsKey(ServiceInfo.getKey(serviceName, clusters));
    }

    @Override
    public void close() {
        logger.info("EventDispatcher do shutdown begin");
        notifyId.onSuccess(vertx::undeploy);
        logger.info("EventDispatcher do shutdown stop");
    }

    private class Notify extends AbstractVerticle {
        @Override
        public void start() {
            vertx.setPeriodic(MINUTES * 5, msg -> {
                ServiceInfo serviceInfo = null;
                serviceInfo = serviceInfoQueue.getLast();
                if (serviceInfo == null) {// continue
                    return;
                }
                List<EventListener> listeners = observerMap.get(serviceInfo.getKey());
                if (!CollectionUtils.isEmpty(listeners)) {
                    for (EventListener listener : listeners) {
                        List<Instance> hosts = Collections.unmodifiableList(serviceInfo.getHosts());
                        listener.onEvent(new NamingEvent(serviceInfo.getName(), serviceInfo.getGroupName(), serviceInfo.getClusters(), hosts));
                    }

                }
            });

        }

    }
}
