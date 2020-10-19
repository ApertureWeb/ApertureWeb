package com.aperture.community.message.service.listener;

import com.aperture.community.message.common.MqMap;
import com.aperture.community.message.config.properties.RocketMQProperties;
import com.aperture.community.message.manager.DingMessageListenerImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

/**
 * @author HALOXIAO
 * @since 2020-10-15 21:04
 **/

@Service
@Slf4j
public class DingListener implements ApplicationRunner {

    private final RocketMQProperties rocketMQProperties;
    private final DingMessageListenerImpl dingMessageListenerImpl;
    private DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MqMap.NOTIFY_GROUP.getValue());

    @Autowired
    public DingListener(RocketMQProperties rocketMQProperties, DingMessageListenerImpl dingMessageListenerImpl) {
        this.rocketMQProperties = rocketMQProperties;
        this.dingMessageListenerImpl = dingMessageListenerImpl;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Ding Ding notify start");
        consumer.subscribe(MqMap.NOTIFY_GROUP.getValue(), "");
        consumer.setNamesrvAddr(rocketMQProperties.getNamesrv());
        consumer.registerMessageListener(dingMessageListenerImpl);
        consumer.start();

    }
}
