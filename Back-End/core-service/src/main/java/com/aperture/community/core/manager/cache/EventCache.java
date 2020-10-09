package com.aperture.community.core.manager.cache;

import com.aperture.community.core.common.map.cache.RedisContentMap;
import com.aperture.community.core.common.status.EventStatus;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.vo.EventVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Aspect
@Component
public class EventCache {

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public EventCache(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 三联Cache
     */
    @Around(value = "execution(* com.aperture.community.core.manager.EventManager.getEventVO(..))&&args(id,status)", argNames = "pjp,id,status")
    public MessageDto<EventVO> eventCache(ProceedingJoinPoint pjp, Long id, EventStatus status) throws Throwable {
        List<Object> dataList = null;
        if (status.getValue().equals(EventStatus.ARTICLE.getValue())) {
            stringRedisTemplate.multi();
            stringRedisTemplate.opsForZSet().score(RedisContentMap.ARTICLE_CONTENT_LIKE.getValue(), id);
            stringRedisTemplate.opsForZSet().score(RedisContentMap.ARTICLE_CONTENT_DONUT.getValue(), id);
            stringRedisTemplate.opsForZSet().score(RedisContentMap.ARTICLE_CONTENT_STORE.getValue(), id);
            dataList = stringRedisTemplate.exec();
        } else if (status.getValue().equals(EventStatus.VIDEO.getValue())) {
            stringRedisTemplate.multi();
            stringRedisTemplate.opsForZSet().score(RedisContentMap.VIDEO_CONTENT_LIKE.getValue(), id);
            stringRedisTemplate.opsForZSet().score(RedisContentMap.VIDEO_CONTENT_DONUT.getValue(), id);
            stringRedisTemplate.opsForZSet().score(RedisContentMap.VIDEO_CONTENT_STORE.getValue(), id);
            dataList = stringRedisTemplate.exec();
        }
        if (dataList == null || dataList.stream().filter(Objects::nonNull).count() != 3) {
            MessageDto<EventVO> result = (MessageDto<EventVO>) pjp.proceed();
            if (!result.getFlag()) {
                return result;
            }
            if (status.getValue().equals(EventStatus.ARTICLE.getValue())) {
                stringRedisTemplate.multi();
                stringRedisTemplate.opsForZSet().add(RedisContentMap.ARTICLE_CONTENT_LIKE.getValue(), String.valueOf(id), result.getData().getLike());
                stringRedisTemplate.opsForZSet().add(RedisContentMap.ARTICLE_CONTENT_DONUT.getValue(), String.valueOf(id), result.getData().getDonut());
                stringRedisTemplate.opsForZSet().add(RedisContentMap.ARTICLE_CONTENT_STORE.getValue(), String.valueOf(id), result.getData().getStore());
                stringRedisTemplate.exec();
            } else if (status.getValue().equals(EventStatus.VIDEO.getValue())) {
                stringRedisTemplate.multi();
                stringRedisTemplate.opsForZSet().add(RedisContentMap.VIDEO_CONTENT_LIKE.getValue(), String.valueOf(id), result.getData().getLike());
                stringRedisTemplate.opsForZSet().add(RedisContentMap.VIDEO_CONTENT_DONUT.getValue(), String.valueOf(id), result.getData().getDonut());
                stringRedisTemplate.opsForZSet().add(RedisContentMap.VIDEO_CONTENT_STORE.getValue(), String.valueOf(id), result.getData().getStore());
                stringRedisTemplate.exec();
            }
            return result;
        }
        EventVO eventVO = new EventVO((Integer) dataList.get(0), (Integer) dataList.get(1), (Integer) dataList.get(2));
        return new MessageDto<>("success", eventVO, true);
    }

    @Around(value = "execution(* com.aperture.community.core.manager.EventManager.deleteEvent(..))&&args(id,status)", argNames = "pjp,id,status")
    public MessageDto<Boolean> deleteEventCache(ProceedingJoinPoint pjp, Long id, EventStatus status) throws Throwable {
        MessageDto<Boolean> result = (MessageDto<Boolean>) pjp.proceed();
        if (result.getData()) {
            if (EventStatus.ARTICLE.equals(status)) {
                stringRedisTemplate.multi();
                stringRedisTemplate.opsForZSet().remove(RedisContentMap.ARTICLE_COMMENT_LIKE.getValue(), id);
                stringRedisTemplate.opsForZSet().remove(RedisContentMap.ARTICLE_CONTENT_DONUT.getValue(), id);
                stringRedisTemplate.opsForZSet().remove(RedisContentMap.ARTICLE_CONTENT_STORE.getValue(), id);
                stringRedisTemplate.exec();
            } else if (EventStatus.VIDEO.equals(status)) {
                stringRedisTemplate.multi();
                stringRedisTemplate.opsForZSet().remove(RedisContentMap.VIDEO_COMMENT_LIKE.getValue(), id);
                stringRedisTemplate.opsForZSet().remove(RedisContentMap.VIDEO_CONTENT_DONUT.getValue(), id);
                stringRedisTemplate.opsForZSet().remove(RedisContentMap.VIDEO_CONTENT_STORE.getValue(), id);
                stringRedisTemplate.exec();
            }
        }
        return result;
    }


}
