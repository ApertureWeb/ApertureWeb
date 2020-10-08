package com.aperture.community.core.service.impl;

import com.aperture.community.core.common.status.EventStatus;
import com.aperture.community.core.manager.EventManager;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.param.CmsEventParam;
import com.aperture.community.core.module.vo.EventVO;
import com.aperture.community.core.service.CmsEventService;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;


@Service
public class CmsEventServiceImpl implements CmsEventService {

    private EventManager eventManager;
    private final Cache<Long, AtomicInteger> likeLocalCache;
    private final Cache<Long, AtomicInteger> feedLocalCache;
    private final Cache<Long, AtomicInteger> storeLocalCache;

    public CmsEventServiceImpl(EventManager eventManager,
                               @Qualifier("ArticleLikeEventCache") Cache<Long, AtomicInteger> articleLikeLocalCache,
                               @Qualifier("ArticleDonutEventCache") Cache<Long, AtomicInteger> articleDonutLocalCache,
                               @Qualifier("ArticleStoreEventCache") Cache<Long, AtomicInteger> articleStoreLocalCache) {
        this.eventManager = eventManager;
        this.likeLocalCache = articleLikeLocalCache;
        this.feedLocalCache = articleDonutLocalCache;
        this.storeLocalCache = articleStoreLocalCache;
    }


//    TODO 不可以重复点击

    @Override
    public void like(Long id) {
        assert id != null;
        AtomicInteger cache = likeLocalCache.getIfPresent(id);
        if (cache != null) {
            cache.addAndGet(1);
        } else {
            synchronized (likeLocalCache) {
                AtomicInteger tempCache = likeLocalCache.getIfPresent(id);
                if (tempCache == null) {
                    likeLocalCache.put(id, new AtomicInteger(1));
                }
            }
        }
    }


    @Override
    public void feed(Long id) {
        AtomicInteger cache = feedLocalCache.getIfPresent(id);
        if (cache != null) {
            cache.addAndGet(1);
        } else {
            synchronized (feedLocalCache) {
                AtomicInteger tempCache = feedLocalCache.getIfPresent(id);
                if (tempCache == null) {
                    feedLocalCache.put(id, new AtomicInteger(1));
                }
            }
        }
    }

    @Override
    public void store(Long id) {
        AtomicInteger cache = storeLocalCache.getIfPresent(id);
        if (cache != null) {
            cache.addAndGet(1);
        } else {
            synchronized (storeLocalCache) {
                AtomicInteger tempCache = storeLocalCache.getIfPresent(id);
                if (tempCache == null) {
                    storeLocalCache.put(id, new AtomicInteger(1));
                }
            }
        }
    }

    @Override
    public MessageDto<EventVO> getEventVO(CmsEventParam eventParam) {
        EventStatus status;
        switch (eventParam.getType()) {
            case (1):
                status = EventStatus.ARTICLE;
                break;
            case (0):
                status = EventStatus.VIDEO;
                break;
            default:
                throw new IllegalArgumentException("类型错误");
        }
        return eventManager.getEventVO(eventParam.getContentId(), status);
    }
}
