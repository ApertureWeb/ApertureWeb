package com.aperture.community.core.manager.schedule;

import com.aperture.community.core.common.map.redis.RedisContentMap;
import com.github.benmanes.caffeine.cache.Cache;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    Cache<Long, AtomicInteger> likeLocalCache;
    Cache<Long, AtomicInteger> feedLocalCache;
    Cache<Long, AtomicInteger> storeLocalCache;
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    public CacheFlushSchedule(@Qualifier("LikeEventCache") Cache<Long, AtomicInteger> likeLocalCache,
                              @Qualifier("FeedEventCache") Cache<Long, AtomicInteger> feedLocalCache,
                              @Qualifier("StoreEventCache") Cache<Long, AtomicInteger> storeLocalCache,
                              StringRedisTemplate stringRedisTemplate) {
        this.likeLocalCache = likeLocalCache;
        this.feedLocalCache = feedLocalCache;
        this.storeLocalCache = storeLocalCache;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Scheduled(initialDelay = 1000)
    public void flushLikeEventCache() {
        HashMap<Long, AtomicInteger> tempCache = Maps.newHashMap(likeLocalCache.asMap());
        likeLocalCache.invalidateAll();
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

    @Scheduled(initialDelay = 5000)
    public void flushFeedEventCache() {
        HashMap<Long, AtomicInteger> tempCache = Maps.newHashMap(feedLocalCache.asMap());
        feedLocalCache.invalidateAll();
        stringRedisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                StringRedisConnection connection = (StringRedisConnection) operations;
                tempCache.forEach((k, v) -> {
                    connection.incrBy(RedisContentMap.PRE_CONTENT_VALUE_NAMESPACE.getValue() + k + RedisContentMap.SUF_CONTENT_DONUT.getValue(), v.longValue());
                });
                return null;
            }
        });
    }


    @Scheduled(initialDelay = 8000)
    public void flushStoreEventCache() {
        HashMap<Long, AtomicInteger> tempCache = Maps.newHashMap(storeLocalCache.asMap());
        storeLocalCache.invalidateAll();
        stringRedisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                StringRedisConnection connection = (StringRedisConnection) operations;
                tempCache.forEach((k, v) -> {
                    connection.incrBy(RedisContentMap.PRE_CONTENT_VALUE_NAMESPACE.getValue() + k + RedisContentMap.SUF_CONTENT_STORE.getValue(), v.longValue());
                });
                return null;
            }
        });

    }
}
