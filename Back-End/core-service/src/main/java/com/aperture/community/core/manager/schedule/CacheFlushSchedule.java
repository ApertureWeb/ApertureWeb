package com.aperture.community.core.manager.schedule;

import com.aperture.community.core.common.map.redis.RedisContentMap;
import com.github.benmanes.caffeine.cache.Cache;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author HALOXIAO
 * @since 2020-10-06 14:40
 **/
@Component
public class CacheFlushSchedule {

    Cache<Long, AtomicInteger> cache;
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    public CacheFlushSchedule(Cache<Long, AtomicInteger> cache, StringRedisTemplate stringRedisTemplate) {
        this.cache = cache;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Scheduled(initialDelay = 1000)
    public void flushEventCache() {
        HashMap<Long, AtomicInteger> tempCache = Maps.newHashMap(cache.asMap());
        cache.invalidateAll();
        stringRedisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                StringRedisConnection connection = (StringRedisConnection) operations;
                tempCache.forEach((k, v) -> {
                    connection.incrBy(RedisContentMap.PRE_CONTENT_VALUE_NAMESPACE.getValue() + k + RedisContentMap.SUF_CONTENT_LIKE.getValue(), v.longValue());
                });
                return null;
            }
        });

    }
}
