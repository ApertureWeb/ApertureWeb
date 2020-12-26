package com.aperture.community.member.manager.mq.listener;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.aperture.common.utils.CastExcepion;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.manager.CollectionManager;
import com.aperture.community.member.manager.CounterManager;
import com.aperture.community.member.manager.handler.GlobalBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-15 17:36
 * @Description:
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "${mq.member.collection.favorates.topic}", consumerGroup = "${mq.member.consumer.collection.group.name}")
public class CollectionFavoratesListener implements RocketMQListener<MessageExt> {

    private CollectionManager collectionManager;
    private CounterManager counterManager;

    @Autowired
    public CollectionFavoratesListener(CollectionManager collectionManager,
                                       CounterManager counterManager) {
        this.collectionManager = collectionManager;
        this.counterManager = counterManager;
    }

    @Override
    @SentinelResource(blockHandlerClass = GlobalBlockHandler.class)
    public void onMessage(MessageExt messageExt) {
        log.info("接收到收藏夹收藏量+1的消息");
        try {
            String body = new String(messageExt.getBody(), "UTF-8").split("_")[0];
            // 调用收藏夹服务，收藏量+1
            Long favoratesId = Long.parseLong(body);
            collectionManager.getFavoratesService().addFavoratesCollection(favoratesId);
        } catch (UnsupportedEncodingException e) {
            CastExcepion.cast(RESULT_BEAN_STATUS_CODE.TYPE_EXCEPTION);
        }
    }
}
