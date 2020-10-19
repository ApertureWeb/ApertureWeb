package com.aperture.community.message.service.listener;

import com.aperture.community.message.common.MqMap;
import com.aperture.community.message.config.RocketMQProperties;
import com.aperture.community.message.manager.DingNotifyManager;
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
    private final DingNotifyManager dingNotifyManager;
    private DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MqMap.NOTIFY_GROUP.getValue());

    @Autowired
    public DingListener(RocketMQProperties rocketMQProperties, DingNotifyManager dingNotifyManager) {
        this.rocketMQProperties = rocketMQProperties;
        this.dingNotifyManager = dingNotifyManager;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Ding Ding notify start");
        consumer.subscribe(MqMap.NOTIFY_GROUP.getValue(), "");
        consumer.setNamesrvAddr(rocketMQProperties.getNamesrv());
        consumer.registerMessageListener(dingNotifyManager);
        consumer.start();

    }
}
