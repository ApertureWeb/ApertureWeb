package com.aperture.community.core.manager;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HALOXIAO
 * @since 2020-09-23 20:39
 **/
@Service
public class PrimaryIdManager {
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    PrimaryIdManager(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public Long getPrimaryId() {
        return null;
    }

}
