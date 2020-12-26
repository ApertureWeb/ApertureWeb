package com.aperture.community.member.manager;

import com.aperture.community.member.common.map.PrimaryIdMap;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: JayV
 * @Date: 2020-11-15 15:17
 * @Description:
 */
@Service
public class PrimaryIdManager {

    private RestTemplate restTemplate;
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    public PrimaryIdManager(RestTemplate restTemplate, RocketMQTemplate rocketMQTemplate) {
        this.restTemplate = restTemplate;
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public Long getPrimaryId() {
        return restTemplate.getForObject(PrimaryIdMap.ID_SERVICE.getValue(), Long.class);
    }

    public Queue<Long> getPrimaryIdBatch(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("参数有误，count需大于0");
        }
        Queue<Long> ids = new LinkedList<>();
        do {
            count--;
            ids.offer(restTemplate.getForObject(PrimaryIdMap.ID_SERVICE.getValue(), Long.class));
        } while(count > 0);
        return ids;
    }



}