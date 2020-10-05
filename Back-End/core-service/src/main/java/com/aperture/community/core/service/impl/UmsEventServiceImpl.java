package com.aperture.community.core.service.impl;

import com.aperture.community.core.dao.UmsEventMapper;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.param.UmsEventParam;
import com.aperture.community.core.module.vo.EventVO;
import com.aperture.community.core.service.UmsEventService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class UmsEventServiceImpl implements UmsEventService {

    private UmsEventMapper umsEventMapper;
    private StringRedisTemplate stringRedisTemplate;


    public UmsEventServiceImpl(UmsEventMapper umsEventMapper, StringRedisTemplate stringRedisTemplate) {
        this.umsEventMapper = umsEventMapper;
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
    public MessageDto<EventVO> getEventVO(UmsEventParam eventParam) {

        return null;
    }
}