package com.aperture.community.message.component.nacos.client.naming;

import com.aperture.community.message.component.nacos.api.PropertyKeyConst;
import com.aperture.community.message.component.nacos.api.common.Constants;
import com.aperture.community.message.component.nacos.api.exception.NacosException;
import com.aperture.community.message.component.nacos.api.naming.NamingService;
import com.aperture.community.message.component.nacos.api.naming.pojo.Instance;
import com.aperture.community.message.component.nacos.api.naming.pojo.ListView;
import com.aperture.community.message.component.nacos.api.naming.pojo.ServiceInfo;
import com.aperture.community.message.component.nacos.api.selector.AbstractSelector;
import com.aperture.community.message.component.nacos.api.utils.NamingUtils;
import com.aperture.community.message.component.nacos.client.naming.beat.BeatInfo;
import com.aperture.community.message.component.nacos.client.naming.beat.BeatReactor;
import com.aperture.community.message.component.nacos.client.naming.core.EventDispatcher;
import com.aperture.community.message.component.nacos.client.naming.core.HostReactor;
import com.aperture.community.message.component.nacos.client.naming.net.NamingProxy;
import com.aperture.community.message.component.nacos.client.naming.utils.CollectionUtils;
import com.aperture.community.message.component.nacos.client.naming.utils.InitUtils;
import com.aperture.community.message.component.nacos.client.naming.utils.UtilAndComs;
import com.aperture.community.message.component.nacos.client.utils.ValidatorUtils;
import com.aperture.community.message.component.nacos.common.utils.StringUtils;
import com.aperture.community.message.service.listener.EventListener;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

import java.util.ArrayList;
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
    private NamingProxy serverProxy;


    public NacosNamingService(String serverList) throws NacosException {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, serverList);
        init(properties);
    }

    NacosNamingService(Properties properties, Vertx vertx) throws NacosException {
        this.vertx = vertx;
        init(properties);
    }

    private void init(Properties properties) throws NacosException {
        ValidatorUtils.checkInitParam(properties);
        this.namespace = InitUtils.initNamespaceForNaming(properties);
        InitUtils.initSerialization();
        initServerAddr(properties);

        InitUtils.initWebRootContext();
        initCacheDir();
        initLogName(properties);

        //==============核心组件==================
        this.eventDispatcher = new EventDispatcher(vertx);
        this.serverProxy = new NamingProxy(this.namespace, this.endpoint, this.serverList, properties, vertx);
        this.beatReactor = new BeatReactor(this.serverProxy, vertx);
        this.hostReactor = new HostReactor(this.eventDispatcher, this.serverProxy, beatReactor, this.cacheDir, vertx);
        //==============核心组件==================
    }

    @Override
    public void registerInstance(String serviceName, String ip, int port) throws NacosException {
        registerInstance(serviceName, ip, port, Constants.DEFAULT_CLUSTER_NAME);
    }

    @Override
    public void registerInstance(String serviceName, String groupName, String ip, int port) throws NacosException {
        registerInstance(serviceName, groupName, ip, port, Constants.DEFAULT_CLUSTER_NAME);
    }

    @Override
    public void registerInstance(String serviceName, String ip, int port, String clusterName) throws NacosException {
        registerInstance(serviceName, Constants.DEFAULT_GROUP, ip, port, clusterName);
    }

    @Override
    public void registerInstance(String serviceName, String groupName, String ip, int port, String clusterName) throws NacosException {

        Instance instance = new Instance();
        instance.setIp(ip);
        instance.setPort(port);
        instance.setWeight(1.0);
        instance.setClusterName(clusterName);

        registerInstance(serviceName, groupName, instance);
    }

    @Override
    public void registerInstance(String serviceName, Instance instance) throws NacosException {
        registerInstance(serviceName, Constants.DEFAULT_GROUP, instance);
    }

    @Override
    public void registerInstance(String serviceName, String groupName, Instance instance) throws NacosException {
        String groupedServiceName = NamingUtils.getGroupedName(serviceName, groupName);
        if (instance.isEphemeral()) {
            BeatInfo beatInfo = beatReactor.buildBeatInfo(groupedServiceName, instance);
            beatReactor.addBeatInfo(groupedServiceName, beatInfo);
        }
        serverProxy.registerService(groupedServiceName, groupName, instance);
    }


    @Override
    public void deregisterInstance(String serviceName, String ip, int port) throws NacosException {
        deregisterInstance(serviceName, ip, port, Constants.DEFAULT_CLUSTER_NAME);
    }

    @Override
    public void deregisterInstance(String serviceName, String groupName, String ip, int port) throws NacosException {
        deregisterInstance(serviceName, groupName, ip, port, Constants.DEFAULT_CLUSTER_NAME);
    }

    @Override
    public void deregisterInstance(String serviceName, String ip, int port, String clusterName) throws NacosException {
        deregisterInstance(serviceName, Constants.DEFAULT_GROUP, ip, port, clusterName);
    }

    @Override
    public void deregisterInstance(String serviceName, String groupName, String ip, int port, String clusterName)
            throws NacosException {
        Instance instance = new Instance();
        instance.setIp(ip);
        instance.setPort(port);
        instance.setClusterName(clusterName);

        deregisterInstance(serviceName, groupName, instance);
    }

    @Override
    public void deregisterInstance(String serviceName, Instance instance) throws NacosException {
        deregisterInstance(serviceName, Constants.DEFAULT_GROUP, instance);
    }

    @Override
    public void deregisterInstance(String serviceName, String groupName, Instance instance) throws NacosException {
        if (instance.isEphemeral()) {
            beatReactor.removeBeatInfo(NamingUtils.getGroupedName(serviceName, groupName), instance.getIp(),
                    instance.getPort());
        }
        serverProxy.deregisterService(NamingUtils.getGroupedName(serviceName, groupName), instance);
    }

    @Override
    public Future<List<Instance>> getAllInstances(String serviceName) throws NacosException {
        return getAllInstances(serviceName, new ArrayList<String>());
    }

    @Override
    public Future<List<Instance>> getAllInstances(String serviceName, String groupName) throws NacosException {
        return getAllInstances(serviceName, groupName, new ArrayList<String>());
    }

    @Override
    public Future<List<Instance>> getAllInstances(String serviceName, boolean subscribe) throws NacosException {
        return getAllInstances(serviceName, new ArrayList<String>(), subscribe);
    }

    @Override
    public Future<List<Instance>> getAllInstances(String serviceName, String groupName, boolean subscribe)
            throws NacosException {
        return getAllInstances(serviceName, groupName, new ArrayList<String>(), subscribe);
    }

    @Override
    public Future<List<Instance>> getAllInstances(String serviceName, List<String> clusters) throws NacosException {
        return getAllInstances(serviceName, clusters, true);
    }

    @Override
    public Future<List<Instance>> getAllInstances(String serviceName, String groupName, List<String> clusters)
            throws NacosException {
        return getAllInstances(serviceName, groupName, clusters, true);
    }

    @Override
    public Future<List<Instance>> getAllInstances(String serviceName, List<String> clusters, boolean subscribe)
            throws NacosException {
        return getAllInstances(serviceName, Constants.DEFAULT_GROUP, clusters, subscribe);
    }

    @Override
    public Future<List<Instance>> getAllInstances(String serviceName, String groupName, List<String> clusters,
                                                  boolean subscribe) throws NacosException {

        ServiceInfo serviceInfo;
        if (subscribe) {
            //从本地获取实例（默认有1s左右的延迟）
            serviceInfo = hostReactor.getServiceInfo(NamingUtils.getGroupedName(serviceName, groupName),
                    StringUtils.join(clusters, ","));

            List<Instance> list;
            if (serviceInfo == null || CollectionUtils.isEmpty(list = serviceInfo.getHosts()) || serviceInfo.getJsonFromServer().equals(ServiceInfo.EMPTY)) {
                return Future.succeededFuture(new ArrayList<>());
            }
            return Future.succeededFuture(list);
        } else {
            //直接从服务端获取全部实例
            return hostReactor.getServiceInfoDirectlyFromServer(NamingUtils.getGroupedName(serviceName, groupName),
                    StringUtils.join(clusters, ",")).compose(res -> {
                List<Instance> list;
                if (res == null || CollectionUtils.isEmpty(list = res.getHosts())) {
                    return Future.succeededFuture(new ArrayList<>());
                }
                return Future.succeededFuture(list);
            });
        }

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

    private void initServerAddr(Properties properties) {
        serverList = properties.getProperty(PropertyKeyConst.SERVER_ADDR);
        endpoint = InitUtils.initEndpoint(properties);
        if (StringUtils.isNotEmpty(endpoint)) {
            serverList = "";
        }
    }

    private void initCacheDir() {
        cacheDir = System.getProperty("com.alibaba.nacos.naming.cache.dir");
        if (StringUtils.isEmpty(cacheDir)) {
            cacheDir = System.getProperty("user.home") + "/nacos/naming/" + namespace;
        }
    }

    private void initLogName(Properties properties) {
        logName = System.getProperty(UtilAndComs.NACOS_NAMING_LOG_NAME);
        if (StringUtils.isEmpty(logName)) {

            if (properties != null && StringUtils
                    .isNotEmpty(properties.getProperty(UtilAndComs.NACOS_NAMING_LOG_NAME))) {
                logName = properties.getProperty(UtilAndComs.NACOS_NAMING_LOG_NAME);
            } else {
                logName = "naming.log";
            }
        }
    }

}


