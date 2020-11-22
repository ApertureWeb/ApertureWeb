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
    private final Object likeLock = new Object();
    private final Object feedLock = new Object();
    private final Object storeLock = new Object();

    public CmsEventServiceImpl(EventManager eventManager,
                               @Qualifier("ArticleLikeEventCache") Cache<Long, AtomicInteger> articleLikeLocalCache,
                               @Qualifier("ArticleDonutEventCache") Cache<Long, AtomicInteger> articleDonutLocalCache,
                               @Qualifier("ArticleStoreEventCache") Cache<Long, AtomicInteger> articleStoreLocalCache) {
        this.eventManager = eventManager;
        this.likeLocalCache = articleLikeLocalCache;
        this.feedLocalCache = articleDonutLocalCache;
        this.storeLocalCache = articleStoreLocalCache;
    }


    // TODO 需要解决：
    //重复点击行为
    //行为推送（需要进行一个测试），连续的行为是否影响消息模块
    @Override
    public void like(Long id) {
        assert id != null;
        AtomicInteger cache = likeLocalCache.getIfPresent(id);
        if (cache != null) {
            cache.addAndGet(1);
        } else {
            //TODO 继续尝试优化锁
            synchronized (likeLock) {
                AtomicInteger tempCache = likeLocalCache.getIfPresent(id);
                if (tempCache == null) {
                    likeLocalCache.put(id, new AtomicInteger(1));
                } else {
                    tempCache.incrementAndGet();
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
            synchronized (feedLock) {
                AtomicInteger tempCache = feedLocalCache.getIfPresent(id);
                if (tempCache == null) {
                    feedLocalCache.put(id, new AtomicInteger(1));
                } else {
                    tempCache.incrementAndGet();
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
            synchronized (storeLock) {
                AtomicInteger tempCache = storeLocalCache.getIfPresent(id);
                if (tempCache == null) {
                    storeLocalCache.put(id, new AtomicInteger(1));
                } else {
                    tempCache.incrementAndGet();
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
