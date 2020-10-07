package com.aperture.community.core.manager.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author HALOXIAO
 * @since 2020-10-07 18:16
 **/
@Component
public class RedisFlushSchedule {

    private StringRedisTemplate stringRedisTemplate;

    private final int HOUR = 1000 * 60 * 60;


    @Autowired
    public RedisFlushSchedule(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Scheduled(initialDelay = 2 * HOUR)
    public void flushArticleComment() {

    }


}
