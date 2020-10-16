package com.aperture.community.message.service;

import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HALOXIAO
 * @since 2020-10-15 21:04
 **/
@Service
public class DingService {

    private Vertx vertx;
    private final String NOTIFY_GROUP = "notiyyGroup";
    private DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
    private final String ERROR_TOPIC = "error";

    @Autowired
    DingService(Vertx vertx) {
        this.vertx = vertx;
    }


    private void receive() throws MQClientException {


    }

}
