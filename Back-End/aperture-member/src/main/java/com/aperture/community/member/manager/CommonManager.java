package com.aperture.community.member.manager;

import lombok.Getter;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-15 16:08
 * @Description:
 */
@Service
@Getter
public class CommonManager {

    private RestTemplate restTemplate;
    private RocketMQTemplate rocketMQTemplate;
    private StringRedisTemplate stringRedisTemplate;
    private ThreadPoolExecutor threadPoolExecutor;


    @Autowired
    public CommonManager(RestTemplate restTemplate,
                         RocketMQTemplate rocketMQTemplate,
                         StringRedisTemplate stringRedisTemplate,
                         ThreadPoolExecutor threadPoolExecutor) {
        this.restTemplate =restTemplate;
        this.rocketMQTemplate = rocketMQTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
        this.threadPoolExecutor = threadPoolExecutor;
    }

}