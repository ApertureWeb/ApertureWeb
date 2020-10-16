package com.aperture.community.message.test;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author HALOXIAO
 * @since 2020-10-16 20:46
 **/
public class queueProducerTest {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("DEFAULT");
        Message message = new Message();
        message.setTopic("test");
        message.setBody("Producer".getBytes());
        producer.start();
        producer.send(message);

    }

}
