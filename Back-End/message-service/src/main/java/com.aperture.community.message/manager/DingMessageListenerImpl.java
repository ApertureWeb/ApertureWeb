package com.aperture.community.message.manager;

import com.aperture.community.message.config.properties.DingProperties;
import com.fasterxml.jackson.core.json.async.NonBlockingJsonParser;
import com.fasterxml.jackson.core.json.async.NonBlockingJsonParserBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import io.vertx.ext.web.client.WebClient;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

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

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {


        msgs.forEach(p -> {
            webClient.postAbs(dingProperties.getHost()).putHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.getType())
                    .addQueryParam("access_token", dingProperties.getToken()).sendJson(null);
        });
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
