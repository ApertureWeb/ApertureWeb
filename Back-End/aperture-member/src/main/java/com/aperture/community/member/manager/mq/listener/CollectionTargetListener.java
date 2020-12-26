package com.aperture.community.member.manager.mq.listener;

import com.alibaba.fastjson.JSON;
import com.aperture.community.member.manager.CollectionManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-15 17:36
 * @Description:
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "${mq.member.collection.target.topic}", consumerGroup = "${mq.member.consumer.collection.group.name}")
public class CollectionTargetListener implements RocketMQListener<MessageExt> {

    private CollectionManager collectionManager;

    @Autowired
    public CollectionTargetListener(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void onMessage(MessageExt messageExt) {
        log.info("接收到给作品收藏+1消息");
        // TODO 远程调用core服务，作品收藏量+1
    }
}