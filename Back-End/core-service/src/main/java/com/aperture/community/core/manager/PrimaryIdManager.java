package com.aperture.community.core.manager;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Queue;

/**
 * @author HALOXIAO
 * @since 2020-09-23 20:39
 **/
@Service
public class PrimaryIdManager {
    private RocketMQTemplate rocketMQTemplate;
    private RestTemplate restTemplate;
    private DiscoveryClient discoveryClient;


    @Autowired
    PrimaryIdManager(RocketMQTemplate rocketMQTemplate, RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.rocketMQTemplate = rocketMQTemplate;
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    public Long getPrimaryId() {

        return null;
    }

    public Queue<Long> getPrimaryIdBatch(int size){
        if(size<=0){
            throw new IllegalArgumentException("size不能<=0");
        }
        return null;
    }

}
