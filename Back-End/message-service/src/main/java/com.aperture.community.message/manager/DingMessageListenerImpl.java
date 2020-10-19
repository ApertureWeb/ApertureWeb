package com.aperture.community.message.manager;

import com.aperture.community.message.config.DingProperties;
import com.aperture.community.message.config.WebClientProperties;
import com.aperture.community.message.module.dto.DingDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import org.apache.catalina.loader.WebappClassLoaderBase;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class DingMessageListenerImpl implements MessageListenerConcurrently {

    private WebClient webClient;
    private DingProperties dingProperties;
    private ObjectMapper objectMapper;

    @Autowired
    public DingMessageListenerImpl(WebClient webClient, DingProperties dingProperties) {
        this.webClient = webClient;
        this.dingProperties = dingProperties;
    }


    //    TODO 不能一直@人，需要将异常进行集合
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        msgs.forEach(p -> {
            webClient.post(dingProperties.getHost()).addQueryParam("access_token", dingProperties.getToken()).sendJson(null);
        });
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
