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

    Cache<Long, AtomicInteger> articleLikeCache;
    Cache<Long, AtomicInteger> articleDonutCache;
    Cache<Long, AtomicInteger> articleStoreCache;
    Cache<Long, AtomicInteger> videoLikeCache;
    Cache<Long, AtomicInteger> videoDonutCache;
    Cache<Long, AtomicInteger> videoStoreCache;

    @Bean("ArticleLikeEventCache")

    public Cache<Long, AtomicInteger> getArticleLikeLocalCache() {
        articleLikeCache = Caffeine.newBuilder()
                .expireAfterWrite(2, TimeUnit.MINUTES)
                .build();
        return articleLikeCache;
    }

    @Bean("ArticleDonutEventCache")
    public Cache<Long, AtomicInteger> getArticleFeedLocalCache() {
        articleDonutCache = Caffeine.newBuilder().expireAfterWrite(2, TimeUnit.MINUTES)
                .build();
        return articleDonutCache;
    }

    @Bean("ArticleStoreEventCache")
    public Cache<Long, AtomicInteger> getArticleStoreLocalCache() {
        articleStoreCache = Caffeine.newBuilder().expireAfterWrite(2, TimeUnit.MINUTES)
                .build();
        return articleStoreCache;
    }

    //==============================================Article/Video分界线==============================================

    @Bean("VideoLikeEventCache")
    public Cache<Long, AtomicInteger> getVideoLikeLocalCache() {
        articleDonutCache = Caffeine.newBuilder().expireAfterWrite(2, TimeUnit.MINUTES)
                .build();
        return articleDonutCache;
    }

    @Bean("VideoDonutEventCache")
    public Cache<Long, AtomicInteger> getVideoDonutLocalCache() {
        articleDonutCache = Caffeine.newBuilder().expireAfterWrite(2, TimeUnit.MINUTES)
                .build();
        return articleDonutCache;
    }

    @Bean("VideoStoreEventCache")
    public Cache<Long, AtomicInteger> getVideoStoreLocalCache() {
        articleDonutCache = Caffeine.newBuilder().expireAfterWrite(2, TimeUnit.MINUTES)
                .build();
        return articleDonutCache;
    }
}
