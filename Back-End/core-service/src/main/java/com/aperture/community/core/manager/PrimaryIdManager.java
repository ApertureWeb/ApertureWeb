package com.aperture.community.core.manager;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author HALOXIAO
 * @since 2020-09-23 20:39
 **/
@Service
public class PrimaryIdManager {
    private RocketMQTemplate rocketMQTemplate;
    private RestTemplate restTemplate;


    @Autowired
    PrimaryIdManager(RocketMQTemplate rocketMQTemplate,RestTemplate restTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
        this.restTemplate = restTemplate;
    }

    public Long getPrimaryId() {

        return null;
    }

}
