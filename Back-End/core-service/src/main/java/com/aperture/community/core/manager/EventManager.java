package com.aperture.community.core.manager;

import com.aperture.community.core.common.EventStatus;
import com.aperture.community.core.common.map.CmsArticleMap;
import com.aperture.community.core.common.map.CmsEventMap;
import com.aperture.community.core.common.map.CmsVideoMap;
import com.aperture.community.core.common.map.redis.RedisContentMap;
import com.aperture.community.core.dao.CmsArticleMapper;
import com.aperture.community.core.dao.CmsEventMapper;
import com.aperture.community.core.dao.CmsVideoMapper;
import com.aperture.community.core.module.CmsArticleEntity;
import com.aperture.community.core.module.CmsEventEntity;
import com.aperture.community.core.module.CmsVideoEntity;
import com.aperture.community.core.module.converter.CmsEventConverter;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.vo.EventVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventManager {

    private StringRedisTemplate stringRedisTemplate;
    private CmsEventMapper cmsEventMapper;
    private CmsArticleMapper cmsArticleMapper;
    private CmsVideoMapper cmsVideoMapper;





    public MessageDto<EventVO> getEventVO(Long id, EventStatus status) {
        if (status.equals(EventStatus.ARTICLE)) {
            CmsArticleEntity articleEntity = cmsArticleMapper.getOne(new QueryWrapper<CmsArticleEntity>().select("1").eq(CmsArticleMap.ID.getValue(), id));
            if (articleEntity == null) {
                return new MessageDto<>("目标文章不存在", null);
            }
        } else {
            CmsVideoEntity videoEntity = cmsVideoMapper.getOne(new QueryWrapper<CmsVideoEntity>().select("1").eq(CmsVideoMap.ID.getValue(), id));
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
        CmsEventEntity eventEntity = cmsEventMapper.getOne(new QueryWrapper<CmsEventEntity>().select(CmsEventMap.LIKE.getValue(), CmsEventMap.DONUT.getValue(), CmsEventMap.STORE.getValue()).eq(CmsEventMap.CONTENT_ID.getValue(), id));
        EventVO result = CmsEventConverter.INSTANCE.toUmsEventEntity(eventEntity);
        return new MessageDto<>("success", result);
    }

}
