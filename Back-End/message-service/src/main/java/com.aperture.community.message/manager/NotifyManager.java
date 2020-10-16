package com.aperture.community.message.manager;

import com.aperture.community.message.config.DingProperties;
import com.aperture.community.message.config.WebClientProperties;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotifyManager implements MessageListenerConcurrently {

    private WebClient webClient;
    private DingProperties dingProperties;

    @Autowired
    public NotifyManager(WebClient webClient, DingProperties dingProperties) {
        this.webClient = webClient;
        this.dingProperties = dingProperties;
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

        webClient.post(dingProperties.getAddress()).sendJson(null);
        return null;
    }
}
