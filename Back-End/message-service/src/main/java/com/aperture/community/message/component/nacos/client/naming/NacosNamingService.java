package com.aperture.community.message.component.nacos.client.naming;

import com.aperture.community.message.component.nacos.api.exception.NacosException;
import com.aperture.community.message.component.nacos.api.naming.NamingService;
import com.aperture.community.message.component.nacos.api.naming.pojo.Instance;
import com.aperture.community.message.component.nacos.api.naming.pojo.ListView;
import com.aperture.community.message.component.nacos.api.naming.pojo.ServiceInfo;
import com.aperture.community.message.component.nacos.api.selector.AbstractSelector;
import com.aperture.community.message.component.nacos.client.naming.beat.BeatReactor;
import com.aperture.community.message.component.nacos.client.naming.core.EventDispatcher;
import com.aperture.community.message.component.nacos.client.naming.core.HostReactor;
import com.aperture.community.message.component.nacos.client.naming.net.NamingProxy;
import com.aperture.community.message.service.listener.EventListener;
import io.vertx.core.Vertx;

import java.util.List;
import java.util.Properties;

/**
 * @author HALOXIAO
 * @since 2020-10-27 16:43
 **/
public class NacosNamingService implements NamingService {


    /**
     * Each Naming service should have different namespace.
     */
    private String namespace;

    private String endpoint;

    private String serverList;

    /**
     * 默认为 ${user}/nacos/naming
     */
    private String cacheDir;

    private String logName;

    private HostReactor hostReactor;

    private BeatReactor beatReactor;

    private EventDispatcher eventDispatcher;

    private Vertx vertx;

    //    private NamingProxy serverProxy;
    public NamingProxy serverProxy;


    NacosNamingService(Properties properties, Vertx vertx) {
        this.vertx = vertx;
        init(properties);
    }

    private void init(Properties properties) {

    }

    @Override
    public void registerInstance(String serviceName, String ip, int port) throws NacosException {

    }

    @Override
    public void registerInstance(String serviceName, String groupName, String ip, int port) throws NacosException {

    }

    @Override
    public void registerInstance(String serviceName, String ip, int port, String clusterName) throws NacosException {

    }

    @Override
    public void registerInstance(String serviceName, String groupName, String ip, int port, String clusterName) throws NacosException {

    }

    @Override
    public void registerInstance(String serviceName, Instance instance) throws NacosException {

    }

    @Override
    public void registerInstance(String serviceName, String groupName, Instance instance) throws NacosException {

    }

    @Override
    public void deregisterInstance(String serviceName, String ip, int port) throws NacosException {

    }

    @Override
    public void deregisterInstance(String serviceName, String groupName, String ip, int port) throws NacosException {

    }

    @Override
    public void deregisterInstance(String serviceName, String ip, int port, String clusterName) throws NacosException {

    }

    @Override
    public void deregisterInstance(String serviceName, String groupName, String ip, int port, String clusterName) throws NacosException {

    }

    @Override
    public void deregisterInstance(String serviceName, Instance instance) throws NacosException {

    }

    @Override
    public void deregisterInstance(String serviceName, String groupName, Instance instance) throws NacosException {

    }

    @Override
    public List<Instance> getAllInstances(String serviceName) throws NacosException {
        return null;
    }

    @Override
    public List<Instance> getAllInstances(String serviceName, String groupName) throws NacosException {
        return null;
    }

    @Override
    public List<Instance> getAllInstances(String serviceName, boolean subscribe) throws NacosException {
        return null;
    }

    @Override
    public List<Instance> getAllInstances(String serviceName, String groupName, boolean subscribe) throws NacosException {
        return null;
    }

    @Override
    public List<Instance> getAllInstances(String serviceName, List<String> clusters) throws NacosException {
        return null;
    }

    @Override
    public List<Instance> getAllInstances(String serviceName, String groupName, List<String> clusters) throws NacosException {
        return null;
    }

    @Override
    public List<Instance> getAllInstances(String serviceName, List<String> clusters, boolean subscribe) throws NacosException {
        return null;
    }

    @Override
    public List<Instance> getAllInstances(String serviceName, String groupName, List<String> clusters, boolean subscribe) throws NacosException {
        return null;
    }

    @Override
    public List<Instance> selectInstances(String serviceName, boolean healthy) throws NacosException {
        return null;
    }

    @Override
    public List<Instance> selectInstances(String serviceName, String groupName, boolean healthy) throws NacosException {
        return null;
    }

    @Override
    public List<Instance> selectInstances(String serviceName, boolean healthy, boolean subscribe) throws NacosException {
        return null;
    }

    @Override
    public List<Instance> selectInstances(String serviceName, String groupName, boolean healthy, boolean subscribe) throws NacosException {
        return null;
    }

    @Override
    public List<Instance> selectInstances(String serviceName, List<String> clusters, boolean healthy) throws NacosException {
        return null;
    }

    @Override
    public List<Instance> selectInstances(String serviceName, String groupName, List<String> clusters, boolean healthy) throws NacosException {
        return null;
    }

    @Override
    public List<Instance> selectInstances(String serviceName, List<String> clusters, boolean healthy, boolean subscribe) throws NacosException {
        return null;
    }

    @Override
    public List<Instance> selectInstances(String serviceName, String groupName, List<String> clusters, boolean healthy, boolean subscribe) throws NacosException {
        return null;
    }

    @Override
    public Instance selectOneHealthyInstance(String serviceName) throws NacosException {
        return null;
    }

    @Override
    public Instance selectOneHealthyInstance(String serviceName, String groupName) throws NacosException {
        return null;
    }

    @Override
    public Instance selectOneHealthyInstance(String serviceName, boolean subscribe) throws NacosException {
        return null;
    }

    @Override
    public Instance selectOneHealthyInstance(String serviceName, String groupName, boolean subscribe) throws NacosException {
        return null;
    }

    @Override
    public Instance selectOneHealthyInstance(String serviceName, List<String> clusters) throws NacosException {
        return null;
    }

    @Override
    public Instance selectOneHealthyInstance(String serviceName, String groupName, List<String> clusters) throws NacosException {
        return null;
    }

    @Override
    public Instance selectOneHealthyInstance(String serviceName, List<String> clusters, boolean subscribe) throws NacosException {
        return null;
    }

    @Override
    public Instance selectOneHealthyInstance(String serviceName, String groupName, List<String> clusters, boolean subscribe) throws NacosException {
        return null;
    }

    @Override
    public void subscribe(String serviceName, EventListener listener) throws NacosException {

    }

    @Override
    public void subscribe(String serviceName, String groupName, EventListener listener) throws NacosException {

    }

    @Override
    public void subscribe(String serviceName, List<String> clusters, EventListener listener) throws NacosException {

    }

    @Override
    public void subscribe(String serviceName, String groupName, List<String> clusters, EventListener listener) throws NacosException {

    }

    @Override
    public void unsubscribe(String serviceName, EventListener listener) throws NacosException {

    }

    @Override
    public void unsubscribe(String serviceName, String groupName, EventListener listener) throws NacosException {

    }

    @Override
    public void unsubscribe(String serviceName, List<String> clusters, EventListener listener) throws NacosException {

    }

    @Override
    public void unsubscribe(String serviceName, String groupName, List<String> clusters, EventListener listener) throws NacosException {

    }

    @Override
    public ListView<String> getServicesOfServer(int pageNo, int pageSize) throws NacosException {
        return null;
    }

    @Override
    public ListView<String> getServicesOfServer(int pageNo, int pageSize, String groupName) throws NacosException {
        return null;
    }

    @Override
    public ListView<String> getServicesOfServer(int pageNo, int pageSize, AbstractSelector selector) throws NacosException {
        return null;
    }

    @Override
    public ListView<String> getServicesOfServer(int pageNo, int pageSize, String groupName, AbstractSelector selector) throws NacosException {
        return null;
    }

    @Override
    public List<ServiceInfo> getSubscribeServices() throws NacosException {
        return null;
    }

    @Override
    public String getServerStatus() {
        return null;
    }

    @Override
    public void shutDown() throws NacosException {

    }
}


