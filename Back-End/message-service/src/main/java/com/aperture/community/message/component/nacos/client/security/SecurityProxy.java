package com.aperture.community.message.component.nacos.client.security;

import com.aperture.community.message.component.nacos.api.PropertyKeyConst;
import com.aperture.community.message.component.nacos.api.WebClientFactory;
import com.aperture.community.message.component.nacos.api.common.Constants;
import com.aperture.community.message.component.nacos.common.http.param.Header;
import com.aperture.community.message.component.nacos.common.utils.JacksonUtils;
import com.aperture.community.message.component.nacos.common.utils.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Future;
import io.vertx.ext.web.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author HALOXIAO
 * @since 2020-10-30 19:53
 **/
public class SecurityProxy {


    private static final Logger SECURITY_LOGGER = LoggerFactory.getLogger(SecurityProxy.class);

    private static final String LOGIN_URL = "/v1/auth/users/login";

    private final WebClient webClient;

    private String contextPath;


    /**
     * User's name.
     */
    private final String username;

    /**
     * User's password.
     */
    private final String password;

    /**
     * A token to take with when sending request to Nacos server.
     */
    private String accessToken;

    /**
     * TTL of token in seconds.
     */
    private long tokenTtl;

    /**
     * Last timestamp refresh security info from server.
     */
    private long lastRefreshTime;

    /**
     * time window to refresh security info in seconds.
     */
    private long tokenRefreshWindow;

    /**
     * Construct from properties, keeping flexibility.
     *
     * @param properties a bunch of properties to read
     */
    public SecurityProxy(Properties properties) {

        username = properties.getProperty(PropertyKeyConst.USERNAME, StringUtils.EMPTY);
        password = properties.getProperty(PropertyKeyConst.PASSWORD, StringUtils.EMPTY);
        contextPath = properties.getProperty(PropertyKeyConst.CONTEXT_PATH, "/nacos");
        contextPath = contextPath.startsWith("/") ? contextPath : "/" + contextPath;
        this.webClient = WebClientFactory.getWebClient();
    }

    /**
     * Login to servers.
     *
     * @param servers server list
     * @return true if login successfully
     */
    public Future<Object> login(List<String> servers) {

        try {
            if ((System.currentTimeMillis() - lastRefreshTime) < TimeUnit.SECONDS
                    .toMillis(tokenTtl - tokenRefreshWindow)) {
                return Future.succeededFuture();
            }

            for (String server : servers) {
                if (login(server).succeeded()) {
                    lastRefreshTime = System.currentTimeMillis();
                    return Future.succeededFuture();
                }
            }
        } catch (Throwable ignore) {

        }

        return Future.failedFuture("login fail");
    }

    /**
     * Login to server.
     *
     * @param server server address
     * @return true if login successfully
     */
    public Future<Object> login(String server) {
        if (StringUtils.isNotBlank(username)) {
            Map<String, String> bodyMap = new HashMap<String, String>(2);
            bodyMap.put("password", password);
            String url = "http://" + server + contextPath + LOGIN_URL;
            if (server.contains(Constants.HTTP_PREFIX)) {
                url = server + contextPath + LOGIN_URL;
            }
            return webClient.postAbs(url).addQueryParam("username", username)
                    .putHeaders(Header.EMPTY.getMultiMap()).sendJson(bodyMap).compose(res -> {
                        if (res.statusCode() == HttpResponseStatus.OK.code()) {
                            JsonNode obj = JacksonUtils.toObj(res.bodyAsString());
                            if (obj.has(Constants.ACCESS_TOKEN)) {
                                accessToken = obj.get(Constants.ACCESS_TOKEN).asText();
                                tokenTtl = obj.get(Constants.TOKEN_TTL).asInt();
                                tokenRefreshWindow = tokenTtl / 10;
                            }

                            return Future.succeededFuture();
                        } else {
                            SECURITY_LOGGER.error("login fail:{}", JacksonUtils.toJson(res.bodyAsString()));
                            return Future.failedFuture("login fail");
                        }
                    }).onFailure(err -> {
                        SECURITY_LOGGER.error("login fail:{}", JacksonUtils.toJson(err.getMessage()));
                    });
        }
        return Future.succeededFuture();
    }

}
