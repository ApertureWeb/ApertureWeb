package com.aperture.community.core.manager;

import com.aperture.community.core.common.map.UmsEventMap;
import com.aperture.community.core.common.map.redis.RedisContentMap;
import com.aperture.community.core.dao.UmsEventMapper;
import com.aperture.community.core.module.UmsEventEntity;
import com.aperture.community.core.module.converter.UmsEventConverter;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.vo.EventVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventManager {

    private StringRedisTemplate stringRedisTemplate;
    private UmsEventMapper umsEventMapper;


    public MessageDto<EventVO> getEventVO(Long id) {
        String likePosition = RedisContentMap.PRE_CONTENT_VALUE_NAMESPACE.getValue() + id + RedisContentMap.SUF_CONTENT_LIKE.getValue();
        String donutPosition = RedisContentMap.PRE_CONTENT_VALUE_NAMESPACE.getValue() + id + RedisContentMap.SUF_CONTENT_DONUT.getValue();
        String storePosition = RedisContentMap.PRE_CONTENT_VALUE_NAMESPACE.getValue() + id + RedisContentMap.SUF_CONTENT_STORE.getValue();
        List<String> results = stringRedisTemplate.opsForValue().multiGet(List.of(likePosition, donutPosition, storePosition));
        if (results == null || results.size() != 3) {
            return new MessageDto<>("获取Event缓存失败", null);
        }
        EventVO eventVO = new EventVO(Integer.valueOf(results.get(0)), Integer.valueOf(results.get(1)), Integer.valueOf(results.get(2)));
        //     real
        UmsEventEntity eventEntity = umsEventMapper.getOne(new QueryWrapper<UmsEventEntity>().select(UmsEventMap.LIKE.getValue(), UmsEventMap.DONUT.getValue(), UmsEventMap.STORE.getValue()).eq(UmsEventMap.CONTENT_ID.getValue(), id));
        EventVO result = UmsEventConverter.INSTANCE.toUmsEventEntity(eventEntity);
        return new MessageDto<>("success", result);
    }

}
