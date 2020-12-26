package com.aperture.community.member.manager.mq.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-15 16:57
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "mq.member")
public class CollectionFavoratesProperties {

    /**
     mq.member.consumer.collection.group.name=member_consumer_collection_group
     mq.member.collection.target.topic=collection_target_topic
     mq.member.collection.tag.confirm=collection_confirm
     mq.member.collection.tag.cancel=collection_cancel
     */

    @Value("${mq.member.consumer.collection.group.name}")
    private String consumerGroupName;
    @Value("${mq.member.collection.favorates.topic}")
    private String topic;
    @Value("${mq.member.collection.tag.confirm}")
    private String tagConfirm;
    @Value("${mq.member.collection.tag.cancel}")
    private String tagCancel;
}