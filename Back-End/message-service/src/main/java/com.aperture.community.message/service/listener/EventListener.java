package com.aperture.community.message.service.listener;

import com.aperture.community.message.common.MqMap;
import com.aperture.community.message.config.RocketMQProperties;
import com.aperture.community.message.manager.EventMessageListenerImpl;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

/**
 * @author HALOXIAO
 * @since 2020-10-16 21:16
 **/
@Service
@Slf4j
public class EventListener implements ApplicationRunner {

    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MqMap.EVENT_GROUP.getValue());
    private final RocketMQProperties properties;

    private final EventMessageListenerImpl eventMessageListener;

    @Autowired
    public EventListener(RocketMQProperties properties, EventMessageListenerImpl eventMessageListener) {
        this.properties = properties;
        this.eventMessageListener = eventMessageListener;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("event service start");
        consumer.subscribe(MqMap.EVENT_TOPIC.getValue(), "");
        consumer.setNamesrvAddr(properties.getNamesrv());
        consumer.registerMessageListener(eventMessageListener);
        consumer.start();
    }
}
