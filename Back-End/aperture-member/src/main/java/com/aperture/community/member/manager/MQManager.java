package com.aperture.community.member.manager;

import com.aperture.community.member.manager.mq.properties.CollectionFavoratesProperties;
import com.aperture.community.member.manager.mq.properties.CollectionTargetProperties;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-15 18:48
 * @Description:
 */
@Service
@Getter
public class MQManager {

    private CollectionTargetProperties toTargetProperties;
    private CollectionFavoratesProperties collectionFavoratesProperties;

    @Autowired
    public MQManager(CollectionTargetProperties toTargetProperties,
                     CollectionFavoratesProperties collectionFavoratesProperties) {
        this.toTargetProperties = toTargetProperties;
        this.collectionFavoratesProperties = collectionFavoratesProperties;
    }

}