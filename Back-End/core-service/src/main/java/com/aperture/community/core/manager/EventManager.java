package com.aperture.community.core.manager;

import com.aperture.community.core.common.EventStatus;
import com.aperture.community.core.common.map.UmsArticleMap;
import com.aperture.community.core.common.map.UmsEventMap;
import com.aperture.community.core.common.map.UmsVideoMap;
import com.aperture.community.core.common.map.redis.RedisContentMap;
import com.aperture.community.core.dao.UmsArticleMapper;
import com.aperture.community.core.dao.UmsEventMapper;
import com.aperture.community.core.dao.UmsVideoMapper;
import com.aperture.community.core.module.UmsArticleEntity;
import com.aperture.community.core.module.UmsEventEntity;
import com.aperture.community.core.module.UmsVideoEntity;
import com.aperture.community.core.module.converter.UmsEventConverter;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.vo.EventVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventManager {

    private StringRedisTemplate stringRedisTemplate;
    private UmsEventMapper umsEventMapper;
    private UmsArticleMapper umsArticleMapper;
    private UmsVideoMapper umsVideoMapper;





    public MessageDto<EventVO> getEventVO(Long id, EventStatus status) {
        if (status.equals(EventStatus.ARTICLE)) {
            UmsArticleEntity articleEntity = umsArticleMapper.getOne(new QueryWrapper<UmsArticleEntity>().select("1").eq(UmsArticleMap.ID.getValue(), id));
            if (articleEntity == null) {
                return new MessageDto<>("目标文章不存在", null);
            }
        } else {
            UmsVideoEntity videoEntity = umsVideoMapper.getOne(new QueryWrapper<UmsVideoEntity>().select("1").eq(UmsVideoMap.ID.getValue(), id));
            if (videoEntity == null) {
                return new MessageDto<>("目标视频不存在", null);
            }
        }
//        =================================cache=================================
        String likePosition = RedisContentMap.PRE_CONTENT_VALUE_NAMESPACE.getValue() + id + RedisContentMap.SUF_CONTENT_LIKE.getValue();
        String donutPosition = RedisContentMap.PRE_CONTENT_VALUE_NAMESPACE.getValue() + id + RedisContentMap.SUF_CONTENT_DONUT.getValue();
        String storePosition = RedisContentMap.PRE_CONTENT_VALUE_NAMESPACE.getValue() + id + RedisContentMap.SUF_CONTENT_STORE.getValue();
        List<String> results = stringRedisTemplate.opsForValue().multiGet(List.of(likePosition, donutPosition, storePosition));
        if (results == null || results.size() != 3) {
            return new MessageDto<>("获取Event缓存失败", null);
        }
        EventVO eventVO = new EventVO(Integer.valueOf(results.get(0)), Integer.valueOf(results.get(1)), Integer.valueOf(results.get(2)));
//        =================================cache=================================
        //     real
        UmsEventEntity eventEntity = umsEventMapper.getOne(new QueryWrapper<UmsEventEntity>().select(UmsEventMap.LIKE.getValue(), UmsEventMap.DONUT.getValue(), UmsEventMap.STORE.getValue()).eq(UmsEventMap.CONTENT_ID.getValue(), id));
        EventVO result = UmsEventConverter.INSTANCE.toUmsEventEntity(eventEntity);
        return new MessageDto<>("success", result);
    }

}
