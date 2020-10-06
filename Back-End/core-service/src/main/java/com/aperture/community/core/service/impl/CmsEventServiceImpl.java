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

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class CmsEventServiceImpl implements CmsEventService {

    private EventManager eventManager;
    private Cache<Long, AtomicInteger> likeLocalCache;
    private Cache<Long, AtomicInteger> feedLocalCache;
    private Cache<Long, AtomicInteger> storeLocalCache;

    public CmsEventServiceImpl(EventManager eventManager,
                               @Qualifier("LikeEventCache") Cache<Long, AtomicInteger> likeLocalCache,
                               @Qualifier("FeedEventCache") Cache<Long, AtomicInteger> feedLocalCache,
                               @Qualifier("StoreEventCache") Cache<Long, AtomicInteger> storeLocalCache) {
        this.eventManager = eventManager;
        this.likeLocalCache = likeLocalCache;
        this.feedLocalCache = feedLocalCache;
        this.storeLocalCache = storeLocalCache;
    }


    @Override
    public MessageDto<Boolean> like(Long id) {
        assert id != null;
        Objects.isNull(likeLocalCache.getIfPresent(id));

        return null;

    }


    @Override
    public MessageDto<Boolean> feed(Long id) {
        return null;
    }

    @Override
    public MessageDto<Boolean> store(Long id) {
        return null;
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