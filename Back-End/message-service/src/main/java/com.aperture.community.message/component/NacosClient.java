package com.aperture.community.message.component;

import com.alibaba.fastjson.JSON;
import com.aperture.community.message.common.NacosUrlMap;
import com.aperture.community.message.config.properties.NacosProperties;
import com.aperture.community.message.module.dto.QueryServerDto;
import com.esotericsoftware.kryo.util.ObjectMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.*;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.net.UnknownServiceException;
import java.util.List;

/**
 * @author HALOXIAO
 * @since 2020-10-19 18:28
 **/
@Component
@Slf4j
public class NacosClient<T> {

    private final Vertx vertx;
    private final WebClient webClient;
    private final NacosProperties properties;
    private final EventBus eventBus;

    @Autowired
    public NacosClient(Vertx vertx, WebClient webClient, NacosProperties properties, EventBus eventBus) {
        this.vertx = vertx;
        this.webClient = webClient;
        this.properties = properties;
        this.eventBus = eventBus;
    }

    /**
     * 注册实例
     */
    public void registerInstance() throws Throwable {
        MultiMap map = MultiMap.caseInsensitiveMultiMap();
        map.add("ip", properties.getServiceIp())
                .add("port", properties.getServicePort())
                .add("serviceName", properties.getServiceName());
        addParam(map, "namespaceId", properties.getNamespaceId())
                .addParam(map, "weight", properties.getWeight().toString())
                .addParam(map, "enabled", properties.getOnlineEnabled().toString())
                .addParam(map, "healthy", properties.getHealthy().toString())
                .addParam(map, "metadata", properties.getMetadata())
                .addParam(map, "clusterName", properties.getClusterName())
                .addParam(map, "groupName", properties.getGroupName())
                .addParam(map, "ephemeral", properties.getEphemeral().toString());
        HttpRequest<Buffer> sender = webClient.post(properties.getPort(), properties.getIp(), NacosUrlMap.DISCOVERY_INSTANCE_REGISTER.getValue());
        sender.queryParams().setAll(map);
        sender.send(res -> {
            requestHandler(res, "register success", "register fail");

        });

    }

    public void deRegisterInstance() {
        MultiMap map = MultiMap.caseInsensitiveMultiMap();
        map.add("ip", properties.getServiceIp())
                .add("port", properties.getServicePort())
                .add("serviceName", properties.getServiceName());
        addParam(map, "namespaceId", properties.getNamespaceId())
                .addParam(map, "clusterName", properties.getClusterName())
                .addParam(map, "groupName", properties.getGroupName())
                .addParam(map, "ephemeral", properties.getEphemeral().toString());
        HttpRequest<Buffer> sender = webClient.delete(properties.getPort(), properties.getIp(), NacosUrlMap.DISCOVERY_INSTANCE_DEREGISTER.getValue());
        sender.queryParams().setAll(map);
        sender.send(res -> {
            requestHandler(res, "deRegister success", "deRegister fail");

        });
    }

    /**
     * Modify an instance of service.
     */
    public void modifyInstance() {
        MultiMap map = MultiMap.caseInsensitiveMultiMap();
        map.add("ip", properties.getServiceIp())
                .add("port", properties.getServicePort())
                .add("serviceName", properties.getServiceName());
        addParam(map, "namespaceId", properties.getNamespaceId())
                .addParam(map, "weight", properties.getWeight().toString())
                .addParam(map, "enabled", properties.getOnlineEnabled().toString())
                .addParam(map, "metadata", properties.getMetadata())
                .addParam(map, "clusterName", properties.getClusterName())
                .addParam(map, "groupName", properties.getGroupName())
                .addParam(map, "ephemeral", properties.getEphemeral().toString());
        HttpRequest<Buffer> sender = webClient.delete(properties.getPort(), properties.getIp(), NacosUrlMap.DISCOVERY_INSTANCE_UPDATE.getValue());
        sender.queryParams().setAll(map);
        sender.send(res -> {
            requestHandler(res, "modify success", "modify fail");
        });
    }

    /**
     * load balanced
     */
    public void queryInstanceList(String requestServiceName, @Nullable String groupName, @Nullable String namespaceId) {
        MultiMap map = MultiMap.caseInsensitiveMultiMap();
        map.add("serviceName", requestServiceName)
                .add("groupName", groupName)
                .add("namespaceId", namespaceId);

    }

    public Future<List<QueryServerDto>> queryServerList(@Nullable Boolean healthy) {
        HttpRequest<Buffer> sender = webClient.get(properties.getPort(), properties.getIp(), NacosUrlMap.DISCOVERY_SERVICE_LIST.getValue());
        if (healthy != null) {
            sender.addQueryParam("healthy", healthy.toString());
        }
        sender.send(res -> {
            if (res.succeeded()) {
                HttpResponse<Buffer> response = res.result();
                List<QueryServerDto> result = JSON.parseArray(response.bodyAsString(), QueryServerDto.class);

            }

        });
        return null;

    }


    private void requestHandler(AsyncResult<HttpResponse<Buffer>> res, String successMsg, String failMsg) {
        if (!res.succeeded()) {
            Throwable failResult = res.cause();
            log.error(failMsg, failResult);
            //快速失败
        }
        log.info(successMsg + res.result().bodyAsString());
    }


    private NacosClient<T> addParam(MultiMap map, String key, String value) {
        if (!value.isEmpty()) {
            map.add(key, value);
        }
        return this;
    }


}
