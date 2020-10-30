package com.aperture.community.message.component.nacos.client.naming.net;

import com.aperture.community.message.component.nacos.api.SystemPropertyKeyConst;
import com.aperture.community.message.component.nacos.api.WebClientFactory;
import com.aperture.community.message.component.nacos.client.naming.utils.CollectionUtils;
import com.aperture.community.message.component.nacos.client.naming.utils.UtilAndComs;
import com.aperture.community.message.component.nacos.client.security.SecurityProxy;
import com.aperture.community.message.component.nacos.common.constant.HttpHeaderConsts;
import com.aperture.community.message.component.nacos.common.http.param.Header;
import com.aperture.community.message.component.nacos.common.utils.UuidUtils;
import com.aperture.community.message.component.nacos.common.utils.VersionUtils;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author HALOXIAO
 * @since 2020-10-27 16:39
 **/


public class NamingProxy implements Closeable {

    private final NacosRestTemplate nacosRestTemplate = NamingHttpClientManager.getInstance().getNacosRestTemplate();
    private final Logger NAMING_LOGGER = LoggerFactory.getLogger(NamingProxy.class);
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
        this.securityProxy = new SecurityProxy(properties, webClient);
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
        try {

            if (!CollectionUtils.isEmpty(serverList)) {
                NAMING_LOGGER.debug("server list provided by user: " + serverList);
                return;
            }
            //如果距离上次刷新的间隔事件少于30s则直接返回，避免频繁调用导致不必性能开销
            if (System.currentTimeMillis() - lastSrvRefTime < vipSrvRefInterMillis) {
                return;
            }
            //获取Nacos Server地址
            List<String> list = getServerListFromEndpoint();

            if (CollectionUtils.isEmpty(list)) {
                throw new Exception("Can not acquire Nacos list");
            }

            if (!CollectionUtils.isEqualCollection(list, serversFromEndpoint)) {
                NAMING_LOGGER.info("[SERVER-LIST] server list is updated: " + list);
            }
            //保存地址
            serversFromEndpoint = list;
            lastSrvRefTime = System.currentTimeMillis();
        } catch (Throwable e) {
            NAMING_LOGGER.warn("failed to update server list", e);
        }
    }

    /**
     * 获取服务器地址
     */
    public List<String> getServerListFromEndpoint() {

        try {
            String urlString = "http://" + endpoint + "/nacos/serverlist";
            Header header = builderHeader();
            //获取服务器地址列表
            HttpRestResult<String> restResult = nacosRestTemplate.get(urlString, header, Query.EMPTY, String.class);
            if (!restResult.ok()) {
                throw new IOException(
                        "Error while requesting: " + urlString + "'. Server returned: " + restResult.getCode());
            }

            String content = restResult.getData();
            List<String> list = new ArrayList<String>();
            //将服务器地址一行一行读入
            for (String line : IoUtils.readLines(new StringReader(content))) {
                if (!line.trim().isEmpty()) {
                    list.add(line.trim());
                }
            }

            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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


    @Override
    public void close() throws IOException {

    }
}



