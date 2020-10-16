package com.aperture.community.message.service;

import com.aperture.community.message.component.DingNotify;
import com.aperture.community.message.config.RocketMQProperties;
import com.aperture.community.message.manager.DingNotifyManager;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;

/**
 * @author HALOXIAO
 * @since 2020-10-15 21:04
 **/

@Service
@Slf4j
public class DingService implements ApplicationRunner {

    private final RocketMQProperties rocketMQProperties;
    private final String DING_TOPIC = "dingding";
    private final DingNotifyManager dingNotifyManager;
    private DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("notify");

    @Autowired
    public DingService(RocketMQProperties rocketMQProperties, DingNotifyManager dingNotifyManager) {
        this.rocketMQProperties = rocketMQProperties;
        this.dingNotifyManager = dingNotifyManager;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Ding Ding notify start");
        consumer.subscribe(DING_TOPIC, "");
        consumer.setNamesrvAddr(rocketMQProperties.getNamesrv());
        consumer.registerMessageListener(dingNotifyManager);
        consumer.start();
    }
}
