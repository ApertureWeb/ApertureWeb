package com.aperture.community.core.manager.schedule;

import com.aperture.community.core.common.map.cache.RedisContentMap;
import com.github.benmanes.caffeine.cache.Cache;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author HALOXIAO
 * @since 2020-10-07 17:41
 **/
@Component
public class VideoContentCacheFlushSchedule {

    Cache<Long, AtomicInteger> videoLikeLocalCache;
    Cache<Long, AtomicInteger> videoDonutLocalCache;
    Cache<Long, AtomicInteger> videoStoreLocalCache;
    StringRedisTemplate stringRedisTemplate;

    public VideoContentCacheFlushSchedule(@Qualifier("VideoLikeEventCache") Cache<Long, AtomicInteger> videoLikeLocalCache,
                                          @Qualifier("VideoDonutEventCache") Cache<Long, AtomicInteger> videoDonutLocalCache,
                                          @Qualifier("VideoStoreEventCache") Cache<Long, AtomicInteger> videoStoreLocalCache,
                                          StringRedisTemplate stringRedisTemplate) {

        this.videoLikeLocalCache = videoLikeLocalCache;
        this.videoDonutLocalCache = videoDonutLocalCache;
        this.videoStoreLocalCache = videoStoreLocalCache;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Scheduled(initialDelay = 1000)
    public void flushVideoLikeEventCache() {
        HashMap<Long, AtomicInteger> tempCache = Maps.newHashMap(videoLikeLocalCache.asMap());
        videoLikeLocalCache.invalidateAll();
        stringRedisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                StringRedisConnection connection = (StringRedisConnection) operations;
                tempCache.forEach((k, v) -> {
                    connection.zIncrBy(RedisContentMap.VIDEO_CONTENT_LIKE.getValue(), v.doubleValue(), String.valueOf(k));
                });
                return null;
            }
        });

    }

    @Scheduled(initialDelay = 5000)
    public void flushVideoDonutEventCache() {
        HashMap<Long, AtomicInteger> tempCache = Maps.newHashMap(videoDonutLocalCache.asMap());
        videoDonutLocalCache.invalidateAll();
        stringRedisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                StringRedisConnection connection = (StringRedisConnection) operations;
                tempCache.forEach((k, v) -> {
                    connection.zIncrBy(RedisContentMap.VIDEO_CONTENT_DONUT.getValue(), v.doubleValue(), String.valueOf(k));
                });
                return null;
            }
        });
    }


    @Scheduled(initialDelay = 8000)
    public void flushVideoStoreEventCache() {
        HashMap<Long, AtomicInteger> tempCache = Maps.newHashMap(videoStoreLocalCache.asMap());
        videoStoreLocalCache.invalidateAll();
        stringRedisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                StringRedisConnection connection = (StringRedisConnection) operations;

                tempCache.forEach((k, v) -> {
                    connection.zIncrBy(RedisContentMap.VIDEO_CONTENT_STORE.getValue(), v.doubleValue(), String.valueOf(k));
                });
                return null;
            }
        });
    }

}
