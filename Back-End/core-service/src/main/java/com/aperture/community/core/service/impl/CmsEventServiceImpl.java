package com.aperture.community.core.service.impl;

import com.aperture.community.core.dao.CmsEventMapper;
import com.aperture.community.core.manager.EventManager;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.param.CmsEventParam;
import com.aperture.community.core.module.vo.EventVO;
import com.aperture.community.core.service.CmsEventService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class CmsEventServiceImpl implements CmsEventService {

    private CmsEventMapper cmsEventMapper;
    private StringRedisTemplate stringRedisTemplate;
    private EventManager eventManager;

    public CmsEventServiceImpl(CmsEventMapper cmsEventMapper, StringRedisTemplate stringRedisTemplate) {
        this.cmsEventMapper = cmsEventMapper;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Override
    public MessageDto<Boolean> like(Long id) {

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

        return null;
    }
}