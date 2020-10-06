package com.aperture.community.core.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author HALOXIAO
 * @since 2020-10-06 16:19
 **/
@Configuration
public class EventLocalCacheConfig {

    Cache<Long, AtomicInteger> likeCache;
    Cache<Long, AtomicInteger> feedCache;
    Cache<Long, AtomicInteger> storeCache;


    @Bean("LikeEventCache")
    public Cache<Long, AtomicInteger> getLikeLocalCache() {
        likeCache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.HOURS)
                .build();
        return likeCache;
    }

    @Bean("FeedEventCache")
    public Cache<Long, AtomicInteger> getFeedLocalCache() {
        return null;
    }

    @Bean("StoreEventCache")
    public Cache<Long, AtomicInteger> getStoreLocalCache() {
        return null;
    }

}
