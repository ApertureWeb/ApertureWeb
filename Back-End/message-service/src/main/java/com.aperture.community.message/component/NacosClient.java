package com.aperture.community.message.component;

import com.aperture.community.message.common.NacosUrlMap;
import com.aperture.community.message.config.properties.NacosProperties;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author HALOXIAO
 * @since 2020-10-19 18:28
 **/
@Component
public class NacosClient<T> {

    private final Vertx vertx;
    private final WebClient webClient;
    private final NacosProperties properties;

    @Autowired
    public NacosClient(Vertx vertx, WebClient webClient, NacosProperties properties) {
        this.vertx = vertx;
        this.webClient = webClient;
        this.properties = properties;
    }

    public void registerInstance() {
        MultiMap map = MultiMap.caseInsensitiveMultiMap();

        map.add("ip", properties.getServiceIp())
                .add("port", properties.getServicePort())
                .add("serviceName", properties.getServiceName());

//        (map, "namespaceId", properties.getNamespaceId());

//                .add("namespaceId", properties.getNamespaceId())
//                .add("enabled", properties.getOnlineEnabled().toString())
//                .add("weight", properties.getWeight().toString())
//                .add("healthy", properties.getHealthy().toString())
//                .add("")
        webClient.post(properties.getPort(), properties.getIp(), NacosUrlMap.DISCOVERY_INSTANCE_REGISTER.getValue())
                .queryParams().setAll(map);

    }


}
