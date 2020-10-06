package com.aperture.community.core.manager.cache;

import com.aperture.community.core.common.map.redis.RedisContentMap;
import com.aperture.community.core.common.status.EventStatus;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.vo.EventVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@Aspect
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
        String likePosition = RedisContentMap.PRE_CONTENT_VALUE_NAMESPACE.getValue() + id + RedisContentMap.SUF_CONTENT_LIKE.getValue();
        String donutPosition = RedisContentMap.PRE_CONTENT_VALUE_NAMESPACE.getValue() + id + RedisContentMap.SUF_CONTENT_DONUT.getValue();
        String storePosition = RedisContentMap.PRE_CONTENT_VALUE_NAMESPACE.getValue() + id + RedisContentMap.SUF_CONTENT_STORE.getValue();
        List<String> results = stringRedisTemplate.opsForValue().multiGet(List.of(likePosition, donutPosition, storePosition));
        if (results == null || results.size() != 3) {
            return (MessageDto<EventVO>) pjp.proceed();
        }
        EventVO eventVO = new EventVO(Integer.valueOf(results.get(0)), Integer.valueOf(results.get(1)), Integer.valueOf(results.get(2)));
        return new MessageDto<>("success", eventVO);
    }




}
