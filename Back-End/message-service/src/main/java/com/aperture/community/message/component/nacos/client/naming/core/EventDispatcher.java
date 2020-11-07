package com.aperture.community.message.component.nacos.client.naming.core;

import com.aperture.community.message.component.nacos.api.naming.listener.EventListener;
import com.aperture.community.message.component.nacos.api.naming.listener.NamingEvent;
import com.aperture.community.message.component.nacos.api.naming.pojo.Instance;
import com.aperture.community.message.component.nacos.api.naming.pojo.ServiceInfo;
import com.aperture.community.message.component.nacos.client.naming.utils.CollectionUtils;
import com.aperture.community.message.component.nacos.common.lifecycle.Closeable;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author HALOXIAO
 * @since 2020-10-27 16:37
 **/
public class EventDispatcher implements Closeable {


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
        Notify notify = new Notify();
        notify.start();
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


    /**
     * Add listener.
     *
     * @param serviceInfo service info
     * @param clusters    clusters
     * @param listener    listener
     */
    public void addListener(ServiceInfo serviceInfo, String clusters, EventListener listener) {

        logger.info("[LISTENER] adding " + serviceInfo.getName() + " with " + clusters + " to listener map");
        List<EventListener> observers = Collections.synchronizedList(new ArrayList<EventListener>());
        observers.add(listener);

        observers = observerMap.putIfAbsent(ServiceInfo.getKey(serviceInfo.getName(), clusters), observers);
        if (observers != null) {
            observers.add(listener);
        }

        serviceChanged(serviceInfo);
    }


    public List<ServiceInfo> getSubscribeServices() {
        List<ServiceInfo> serviceInfos = new ArrayList<ServiceInfo>();
        for (String key : observerMap.keySet()) {
            serviceInfos.add(ServiceInfo.fromKey(key));
        }
        return serviceInfos;
    }


    public boolean isSubscribed(String serviceName, String clusters) {
        return observerMap.containsKey(ServiceInfo.getKey(serviceName, clusters));
    }

    @Override
    public void shutdown() {
        logger.info("EventDispatcher do shutdown begin");
        notifyId.onSuccess(vertx::undeploy);
        logger.info("EventDispatcher do shutdown stop");
    }

    private class Notify {

        public void start() {
            vertx.setPeriodic(MINUTES * 5, msg -> {
                ServiceInfo serviceInfo = null;
                try {
                    serviceInfo = serviceInfoQueue.getLast();
                } catch (NoSuchElementException e) {
                    logger.debug(e.getMessage());
                }
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
