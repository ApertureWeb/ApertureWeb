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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author HALOXIAO
 * @since 2020-10-06 14:40
 **/
@Component
public class ArticleContentCacheFlushSchedule {

    Cache<Long, AtomicInteger> articleLikeLocalCache;
    Cache<Long, AtomicInteger> articleDonutLocalCache;
    Cache<Long, AtomicInteger> articleStoreLocalCache;

    StringRedisTemplate stringRedisTemplate;


    @Autowired
    public ArticleContentCacheFlushSchedule(@Qualifier("ArticleLikeEventCache") Cache<Long, AtomicInteger> articleLikeLocalCache,
                                            @Qualifier("ArticleDonutEventCache") Cache<Long, AtomicInteger> articleDonutLocalCache,
                                            @Qualifier("ArticleStoreEventCache") Cache<Long, AtomicInteger> articleStoreLocalCache,
                                            StringRedisTemplate stringRedisTemplate) {
        this.articleLikeLocalCache = articleLikeLocalCache;
        this.articleDonutLocalCache = articleDonutLocalCache;
        this.articleStoreLocalCache = articleStoreLocalCache;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Scheduled(initialDelay = 1000)
    public void flushArticleLikeEventCache() {
        HashMap<Long, AtomicInteger> tempCache = Maps.newHashMap(articleLikeLocalCache.asMap());
        articleLikeLocalCache.invalidateAll();
        stringRedisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                StringRedisConnection connection = (StringRedisConnection) operations;
                tempCache.forEach((k, v) -> {
                    connection.zIncrBy(RedisContentMap.ARTICLE_CONTENT_LIKE.getValue(), v.doubleValue(), String.valueOf(k));
                });
                return null;
            }
        });

    }

    @Scheduled(initialDelay = 5000)
    public void flushArticleDonutEventCache() {
        HashMap<Long, AtomicInteger> tempCache = Maps.newHashMap(articleDonutLocalCache.asMap());
        articleDonutLocalCache.invalidateAll();
        stringRedisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                StringRedisConnection connection = (StringRedisConnection) operations;
                tempCache.forEach((k, v) -> {
                    connection.zIncrBy(RedisContentMap.ARTICLE_CONTENT_DONUT.getValue(), v.doubleValue(), String.valueOf(k));
                });
                return null;
            }
        });
    }


    @Scheduled(initialDelay = 8000)
    public void flushArticleStoreEventCache() {
        HashMap<Long, AtomicInteger> tempCache = Maps.newHashMap(articleStoreLocalCache.asMap());
        articleStoreLocalCache.invalidateAll();
        stringRedisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                StringRedisConnection connection = (StringRedisConnection) operations;

                tempCache.forEach((k, v) -> {
                    connection.zIncrBy(RedisContentMap.ARTICLE_CONTENT_STORE.getValue(), v.doubleValue(), String.valueOf(k));
                });
                return null;
            }
        });
    }

}
