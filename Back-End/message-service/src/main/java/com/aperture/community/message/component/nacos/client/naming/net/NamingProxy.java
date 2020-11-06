package com.aperture.community.message.component.nacos.client.naming.net;

import com.aperture.community.message.component.nacos.api.PropertyKeyConst;
import com.aperture.community.message.component.nacos.api.SystemPropertyKeyConst;
import com.aperture.community.message.component.nacos.api.WebClientFactory;
import com.aperture.community.message.component.nacos.api.common.Constants;
import com.aperture.community.message.component.nacos.api.exception.NacosException;
import com.aperture.community.message.component.nacos.api.naming.CommonParams;
import com.aperture.community.message.component.nacos.api.naming.pojo.Instance;
import com.aperture.community.message.component.nacos.client.config.impl.SpasAdapter;
import com.aperture.community.message.component.nacos.client.naming.beat.BeatInfo;
import com.aperture.community.message.component.nacos.client.naming.utils.CollectionUtils;
import com.aperture.community.message.component.nacos.client.naming.utils.NetUtils;
import com.aperture.community.message.component.nacos.client.naming.utils.SignUtil;
import com.aperture.community.message.component.nacos.client.naming.utils.UtilAndComs;
import com.aperture.community.message.component.nacos.client.security.SecurityProxy;
import com.aperture.community.message.component.nacos.client.utils.AppNameUtils;
import com.aperture.community.message.component.nacos.client.utils.JobUtils;
import com.aperture.community.message.component.nacos.client.utils.TemplateUtils;
import com.aperture.community.message.component.nacos.common.constant.HttpHeaderConsts;
import com.aperture.community.message.component.nacos.common.http.param.Header;
import com.aperture.community.message.component.nacos.common.utils.IoUtils;
import com.aperture.community.message.component.nacos.common.utils.JacksonUtils;
import com.aperture.community.message.component.nacos.common.utils.UuidUtils;
import com.aperture.community.message.component.nacos.common.utils.VersionUtils;
import com.fasterxml.jackson.databind.JsonNode;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author HALOXIAO
 * @since 2020-10-27 16:39
 **/


public class NamingProxy implements Closeable {

    private final Logger logger = LoggerFactory.getLogger(NamingProxy.class);
    private static final int DEFAULT_SERVER_PORT = 8848;
    private final WebClient webClient;
    private final Vertx vertx;
    private int serverPort = DEFAULT_SERVER_PORT;

    private final String namespaceId;
    //server集群扩缩容信息获取
    private final String endpoint;

    //当serverList size为1时，nacosDeomain会被设置为服务ip地址
    private String nacosDomain;

    private List<String> serverList;
    //扩缩容后服务地址
    private List<String> serversFromEndpoint = new ArrayList<String>();

    private final SecurityProxy securityProxy;

    private long lastSrvRefTime = 0L;

    private final long vipSrvRefInterMillis = TimeUnit.SECONDS.toMillis(30);

    private final long securityInfoRefreshIntervalMills = TimeUnit.SECONDS.toMillis(5);

    private Properties properties;


    public NamingProxy(String namespaceId, String endpoint, String serverList, Properties properties, Vertx vertx) {
        this.vertx = vertx;
        this.webClient = WebClientFactory.getWebClient();
        this.securityProxy = new SecurityProxy(properties);
        this.properties = properties;
        this.setServerPort(DEFAULT_SERVER_PORT);
        this.namespaceId = namespaceId;
        this.endpoint = endpoint;
        if (StringUtils.isNotEmpty(serverList)) {
            this.serverList = Arrays.asList(serverList.split(","));
            if (this.serverList.size() == 1) {
                this.nacosDomain = serverList;
            }
        }
        this.initRefreshTask();
    }

    /**
     * 初始化ServerList刷新任务
     */
    private void initRefreshTask() {
        vertx.setPeriodic(vipSrvRefInterMillis, task -> {
            refreshSrvIfNeed();
        });

        vertx.setPeriodic(securityInfoRefreshIntervalMills, task -> {
            securityProxy.login(getServerList());
        });

        refreshSrvIfNeed();
        this.securityProxy.login(getServerList());
    }

    private List<String> getServerList() {
        List<String> snapshot = serversFromEndpoint;
        if (!CollectionUtils.isEmpty(serverList)) {
            snapshot = serverList;
        }
        return snapshot;
    }

    /**
     * 刷新Nacos Server地址；
     * 从Nacos Server获取一串Nacos Server的地址列表
     */
    private void refreshSrvIfNeed() {

        if (!CollectionUtils.isEmpty(serverList)) {
            logger.debug("server list provided by user: " + serverList);
            return;
        }
        //如果距离上次刷新的间隔事件少于30s则直接返回，避免频繁调用导致不必性能开销
        if (System.currentTimeMillis() - lastSrvRefTime < vipSrvRefInterMillis) {
            return;
        }
        //获取Nacos Server地址
        getServerListFromEndpoint().onSuccess(list -> {
            if (CollectionUtils.isEmpty(list)) {
                logger.warn("Can not acquire Nacos list");
            }
            if (!CollectionUtils.isEqualCollection(list, serversFromEndpoint)) {
                logger.info("[SERVER-LIST] server list is updated: " + list);
            }
            //保存地址
            serversFromEndpoint = list;
            lastSrvRefTime = System.currentTimeMillis();
        }).onFailure(err -> {
            logger.warn(err.getMessage());
        });

    }


    /**
     * 获取服务器地址
     */
    public Future<List<String>> getServerListFromEndpoint() {

        String urlString = "http://" + endpoint + "/nacos/serverlist";
        Header header = builderHeader();
        Future<List<String>> future = Future.succeededFuture(new ArrayList<>());

        //获取服务器地址列表
        return webClient.getAbs(urlString).putHeaders(header.getMultiMap()).ssl(false).send().
                compose(res -> {
                    if (res.statusCode() != HttpResponseStatus.OK.code()) {
                        logger.error("Error while requesting: " + urlString + "'. Server returned: " + res.statusCode());
                        return Future.failedFuture("Error while requesting: " + urlString + "'. Server returned: " + res.statusCode());
                    } else {
                        Buffer content = res.body();
                        //将服务器地址一行一行读入
                        IoUtils.readLines(content).onSuccess(msg -> {
                            for (String line : msg) {
                                if (!line.trim().isEmpty()) {
                                    future.result().add(line.trim());
                                }
                            }
                        });
                        return future;
                    }
                }).onFailure(err -> {
            logger.error("Error while requesting: " + urlString + "; reason:" + err.getMessage());
        });


    }

    /**
     * Build header.
     *
     * @return header
     */
    public Header builderHeader() {
        Header header = Header.newInstance();
        header.addParam(HttpHeaderConsts.CLIENT_VERSION_HEADER.getValue(), VersionUtils.version);
        header.addParam(HttpHeaderConsts.USER_AGENT_HEADER.getValue(), UtilAndComs.VERSION);
        header.addParam(HttpHeaderConsts.ACCEPT_ENCODING.getValue(), "gzip,deflate,sdch");
        header.addParam(HttpHeaderConsts.CONNECTION.getValue(), "Keep-Alive");
        header.addParam(HttpHeaderConsts.REQUEST_ID.getValue(), UuidUtils.generateUuid());
        header.addParam(HttpHeaderConsts.REQUEST_MODULE.getValue(), "Naming");
        return header;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;

        String sp = System.getProperty(SystemPropertyKeyConst.NAMING_SERVER_PORT);
        if (StringUtils.isNotBlank(sp)) {
            this.serverPort = Integer.parseInt(sp);
        }
    }


    /**
     * deregister instance from a service.
     *
     * @param serviceName name of service
     * @param instance    instance
     * @throws NacosException nacos exception
     */
    public void deregisterService(String serviceName, Instance instance) throws NacosException {

        logger.info("[DEREGISTER-SERVICE] {} deregistering service {} with instance: {}", namespaceId, serviceName,
                instance);

        final MultiMap params = MultiMap.caseInsensitiveMultiMap();

        params.add(CommonParams.NAMESPACE_ID, namespaceId);
        params.add(CommonParams.SERVICE_NAME, serviceName);
        params.add(CommonParams.CLUSTER_NAME, instance.getClusterName());
        params.add("ip", instance.getIp());
        params.add("port", String.valueOf(instance.getPort()));
        params.add("ephemeral", String.valueOf(instance.isEphemeral()));

        reqApi(UtilAndComs.nacosUrlInstance, params, HttpMethod.DELETE);
    }

    /**
     * Send beat.
     *
     * @param beatInfo         beat info
     * @param lightBeatEnabled light beat
     * @return beat result
     */
    public Future<JsonNode> sendBeat(BeatInfo beatInfo, boolean lightBeatEnabled) throws NacosException {
        if (logger.isDebugEnabled()) {
            logger.debug("[BEAT] {} sending beat to server: {}", namespaceId, beatInfo.toString());
        }
        Map<String, String> body = new HashMap<>(2);
        if (!lightBeatEnabled) {
            body.put("beat", JacksonUtils.toJson(beatInfo));
        }
        MultiMap params = MultiMap.caseInsensitiveMultiMap();
        params.add(CommonParams.NAMESPACE_ID, namespaceId);
        params.add(CommonParams.SERVICE_NAME, beatInfo.getServiceName());
        params.add(CommonParams.CLUSTER_NAME, beatInfo.getCluster());
        params.add("ip", beatInfo.getIp());
        params.add("port", String.valueOf(beatInfo.getPort()));
        return reqApi(UtilAndComs.nacosUrlBase + "/instance/beat", params, body, getServerList(), HttpMethod.PUT).compose(res -> Future.succeededFuture(JacksonUtils.toObj(res)));

    }


    public Future<String> reqApi(String api, MultiMap params, Map<String, String> body, List<String> servers,
                                 HttpMethod method) throws NacosException {
        if (CollectionUtils.isEmpty(servers) && StringUtils.isEmpty(nacosDomain)) {
            throw new NacosException(NacosException.INVALID_PARAM, "no server available");
        }
        if (servers != null && !servers.isEmpty()) {
            Random random = new Random(System.currentTimeMillis());
            int index = random.nextInt(servers.size());
            //依次向每个服务器发送注册实例信息,直到遍历完服务或者有服务正常响应
            //TODO MD好像没写完
            Iterator<String> iterator = servers.iterator();
            return callServerProxy(api, params, body, nacosDomain, method, servers.iterator())
                    .compose(res -> {
                        if (null == res) {
                            if (StringUtils.isNotBlank(nacosDomain)) {
                                JobUtils<String> job = new JobUtils<>();
                                return job.attempt(UtilAndComs.REQUEST_DOMAIN_RETRY_COUNT, h -> {
                                    callServer(api, params, body, nacosDomain, method);
                                }).onFailure(err -> {
                                    if (logger.isDebugEnabled()) {
                                        logger.debug("request {} failed.", nacosDomain, err);
                                    }
                                }).compose(inres ->
                                        Future.succeededFuture(inres.getResult())
                                );
                            }
                            return Future.failedFuture("fail to call server");

                        } else {
                            return Future.succeededFuture(res);
                        }
                    });
        }

        //TODO perfect error handle
        return Future.failedFuture(String.format("request:%s failed,servers:%s ", api, servers.toString()));
    }

    public Future<String> reqApi(String api, MultiMap params, HttpMethod method) throws NacosException {
        return reqApi(api, params, Collections.EMPTY_MAP, method);
    }

    public Future<String> reqApi(String api, MultiMap params, Map<String, String> body, HttpMethod method)
            throws NacosException {
        return reqApi(api, params, body, getServerList(), method);
    }


    /**
     * proxy for #callServer
     *
     * @return if false content == null
     */
    private Future<String> callServerProxy(String api, MultiMap params, Map<String, String> body, String curServer,
                                           HttpMethod method, Iterator<String> servers) {
        while (servers.hasNext()) {
            return callServer(api, params, body, curServer, method).onFailure(err -> {
                callServerProxy(api, params, body, servers.next(), method, servers);
            }).compose(res -> Future.succeededFuture(res));
        }
        return Future.succeededFuture(null);
    }

    public Future<String> callServer(String api, MultiMap params, Map<String, String> body, String curServer,
                                     HttpMethod method) {

        long start = System.currentTimeMillis();
        injectSecurityInfo(params);
        Header header = builderHeader();

        String url;
        //服务器地址是否为Http/Https格式
        if (curServer.startsWith(UtilAndComs.HTTPS) || curServer.startsWith(UtilAndComs.HTTP)) {
            url = curServer + api;
        } else {
            //ip格式，是否带有port
            if (!curServer.contains(UtilAndComs.SERVER_ADDR_IP_SPLITER)) {
                curServer = curServer + UtilAndComs.SERVER_ADDR_IP_SPLITER + serverPort;
            }
            //构建需要访问服务的url
            url = NamingHttpClientManager.getInstance().getPrefix() + curServer + api;
        }
        WebClient webClient = WebClientFactory.getWebClient();
        HttpRequest<Buffer> sender = webClient.requestAbs(method, url).putHeaders(header.getMultiMap());
        sender.queryParams().setAll(params);
        return sender.sendJson(body).onComplete(res -> {
            long end = System.currentTimeMillis();
//            TODO  性能监控：etricsMonitor.getNamingRequestMonitor(method, url, String.valueOf(restResult.getCode()))
//                .observe(end - start);
        }).onFailure(err -> {
            logger.error("[NA] failed to request", err);
        }).compose(res -> {
            if (res.statusCode() == HttpResponseStatus.OK.code()) {
                return Future.succeededFuture(res.bodyAsString());
            } else if (res.statusCode() == HttpResponseStatus.NOT_MODIFIED.code()) {
                return Future.succeededFuture(com.aperture.community.message.component.nacos.common.utils.StringUtils.EMPTY);
            }
            return Future.failedFuture("result code:" + res.statusCode() + ";reason" + res.bodyAsString());
        });

    }

    /**
     * Query instance list.
     *
     * @param serviceName service name
     * @param clusters    clusters
     * @param udpPort     udp port
     * @param healthyOnly healthy only
     * @return instance list
     * @throws NacosException nacos exception
     */

    public Future<String> queryList(String serviceName, String clusters, int udpPort, boolean healthyOnly)
            throws NacosException {

        final MultiMap params = MultiMap.caseInsensitiveMultiMap();
        params.add(CommonParams.NAMESPACE_ID, namespaceId);
        params.add(CommonParams.SERVICE_NAME, serviceName);
        params.add("clusters", clusters);
        params.add("udpPort", String.valueOf(udpPort));
        params.add("clientIP", NetUtils.localIP());
        params.add("healthyOnly", String.valueOf(healthyOnly));

        return reqApi(UtilAndComs.nacosUrlBase + "/instance/list", params, HttpMethod.GET);
    }


    /**
     * register a instance to service with specified instance properties.
     *
     * @param serviceName name of service
     * @param groupName   group of service
     * @param instance    instance to register
     * @throws NacosException nacos exception
     */
    public void registerService(String serviceName, String groupName, Instance instance) throws NacosException {
        logger.info("[REGISTER-SERVICE] {} registering service {} with instance: {}", namespaceId, serviceName,
                instance);
        MultiMap params = MultiMap.caseInsensitiveMultiMap();
        params.add(CommonParams.NAMESPACE_ID, namespaceId);
        params.add(CommonParams.SERVICE_NAME, serviceName);
        params.add(CommonParams.GROUP_NAME, groupName);
        params.add(CommonParams.CLUSTER_NAME, instance.getClusterName());
        params.add("ip", instance.getIp());
        params.add("port", String.valueOf(instance.getPort()));
        params.add("weight", String.valueOf(instance.getWeight()));
        params.add("enable", String.valueOf(instance.isEnabled()));
        params.add("healthy", String.valueOf(instance.isHealthy()));
        params.add("ephemeral", String.valueOf(instance.isEphemeral()));
        params.add("metadata", JacksonUtils.toJson(instance.getMetadata()));
        reqApi(UtilAndComs.nacosUrlInstance, params, HttpMethod.POST);
    }


    private void injectSecurityInfo(MultiMap params) {

        // Inject token if exist:
        if (StringUtils.isNotBlank(securityProxy.getAccessToken())) {
            params.add(Constants.ACCESS_TOKEN, securityProxy.getAccessToken());
        }

        // Inject ak/sk if exist:
        String ak = getAccessKey();
        String sk = getSecretKey();
        params.add("app", AppNameUtils.getAppName());
        if (StringUtils.isNotBlank(ak) && StringUtils.isNotBlank(sk)) {
            try {
                String signData = getSignData(params.get("serviceName"));
                String signature = SignUtil.sign(signData, sk);
                params.add("signature", signature);
                params.add("data", signData);
                params.add("ak", ak);
            } catch (Exception e) {
                logger.error("inject ak/sk failed.", e);
            }
        }
    }

    private static String getSignData(String serviceName) {
        return StringUtils.isNotEmpty(serviceName) ? System.currentTimeMillis() + "@@" + serviceName
                : String.valueOf(System.currentTimeMillis());
    }

    public String getAccessKey() {
        if (properties == null) {
            return SpasAdapter.getAk();
        }

        return TemplateUtils
                .stringEmptyAndThenExecute(properties.getProperty(PropertyKeyConst.ACCESS_KEY), new Callable<String>() {
                    @Override
                    public String call() {
                        return SpasAdapter.getAk();
                    }
                });
    }

    public String getSecretKey() {
        if (properties == null) {
            return SpasAdapter.getSk();
        }

        return TemplateUtils
                .stringEmptyAndThenExecute(properties.getProperty(PropertyKeyConst.SECRET_KEY), new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return SpasAdapter.getSk();
                    }
                });
    }

    @Override
    public void close() throws IOException {

    }


}