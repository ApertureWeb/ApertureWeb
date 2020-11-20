package com.aperture.community.member.config;

import com.aperture.community.member.config.properties.LocalCacheConfigProperties;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Auther: JayV
 * @Date: 2020-11-14 16:32
 * @Description: caffeine本地同步缓存配置
 */
@Configuration
public class LocalCacheConfig {

    @Autowired
    LocalCacheConfigProperties localCacheConfigProperties;

    Cache<Long, AtomicInteger> collectionMemberCache;
    Cache<Long, AtomicInteger> collectionFavoratesCache;
    Cache<Long, AtomicInteger> followMemberCache;
    Cache<Long, AtomicInteger> followGroupCache;


    @Bean("collectionMemberEventCache")
    public Cache<Long, AtomicInteger> getColletionMemberCache() {
        // 自动加载同步
        collectionMemberCache = Caffeine.newBuilder()
                .expireAfterWrite(localCacheConfigProperties.getExpireAfterWrite(), TimeUnit.MINUTES)
                .expireAfterAccess(localCacheConfigProperties.getExpireAfterAccess(), TimeUnit.MINUTES)
                .build();
        return collectionMemberCache;
    }

    @Bean("collectionFavoratesEventCache")
    public Cache<Long, AtomicInteger> getCollectionFavoratesCache() {
        // 自动加载同步
        collectionFavoratesCache = Caffeine.newBuilder()
                .expireAfterWrite(localCacheConfigProperties.getExpireAfterWrite(), TimeUnit.MINUTES)
                .expireAfterAccess(localCacheConfigProperties.getExpireAfterAccess(), TimeUnit.MINUTES)
                .build();
        return collectionFavoratesCache;
    }

    @Bean("followMemberEventCache")
    public Cache<Long, AtomicInteger> getFollowMemberCache() {
        // 自动加载同步
        followMemberCache = Caffeine.newBuilder()
                .expireAfterWrite(localCacheConfigProperties.getExpireAfterWrite(), TimeUnit.MINUTES)
                .expireAfterAccess(localCacheConfigProperties.getExpireAfterAccess(), TimeUnit.MINUTES)
                .build();
        return followMemberCache;
    }

    @Bean("followGroupEventCache")
    public Cache<Long, AtomicInteger> getFollowGroupCache() {
        // 自动加载同步
        followGroupCache = Caffeine.newBuilder()
                .expireAfterWrite(localCacheConfigProperties.getExpireAfterWrite(), TimeUnit.MINUTES)
                .expireAfterAccess(localCacheConfigProperties.getExpireAfterAccess(), TimeUnit.MINUTES)
                .build();
        return followGroupCache;
    }
}