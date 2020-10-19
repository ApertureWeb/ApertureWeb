package com.aperture.community.message.manager;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.SqlConnection;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author HALOXIAO
 * @since 2020-10-19 16:29
 **/
@Component
public class EventMessageListenerImpl implements MessageListenerConcurrently {

    private final MySQLPool pool;
    private final Vertx vertx;

    @Autowired
    public EventMessageListenerImpl(MySQLPool pool, Vertx vertx) {
        this.pool = pool;
        this.vertx = vertx;
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

        return null;
    }
}
