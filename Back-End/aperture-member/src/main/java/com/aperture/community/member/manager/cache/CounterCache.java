package com.aperture.community.member.manager.cache;

import com.aperture.community.member.common.map.CounterMap;
import com.aperture.community.member.common.map.cache.RedisCounterMap;
import com.aperture.community.member.config.ThreadPoolConfig;
import com.aperture.community.member.manager.CollectionManager;
import com.aperture.community.member.manager.CommonManager;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.vo.CounterVo;
import com.sun.jmx.snmp.tasks.ThreadService;
import io.swagger.models.auth.In;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.support.collections.RedisMap;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin2.message.Message;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-16 09:10
 * @Description:
 */
@Aspect
@Component
public class CounterCache {

    private CommonManager commonManager;

    @Autowired
    public CounterCache(CommonManager commonManager) {
        this.commonManager = commonManager;
    }

    /**
     * 切入点
     */
    @Pointcut("execution(* com.aperture.community.member.manager.CounterManager.getCounterVo(..))")
    public void getCounterPointCut() {}

    @Pointcut("execution(* com.aperture.community.member.manager.CounterManager.deleteCounterVo(..))")
    public void deleteCounterPointCut() {}

    /**
     * 对getCounterFromDB的方法进行增强，在查询数据库之前查询redis缓存，有则获取，无则从库获取后设置到redis
     * @param pjp
     * @param memberId
     * @return
     */
    @Order(1)
    @Around(value = "getCounterPointCut()&&args(memberId)", argNames = "pjp, memberId")
    public MessageDto<CounterVo> getcounterCache(ProceedingJoinPoint pjp, Long memberId) throws Throwable {
        List<Object> counterList = null;
        commonManager.getStringRedisTemplate().multi();
        commonManager.getStringRedisTemplate().opsForZSet().
                score(RedisCounterMap.MEMBER_ALL_FANS.getValue(), memberId);
        commonManager.getStringRedisTemplate().opsForZSet().
                score(RedisCounterMap.MEMBER_ALL_FAVORATES.getValue(), memberId);
        commonManager.getStringRedisTemplate().opsForZSet().
                score(RedisCounterMap.MEMBER_ALL_FOLLOW.getValue(), memberId);
        commonManager.getStringRedisTemplate().opsForZSet().
                score(RedisCounterMap.MEMBER_ALL_LIKE.getValue(), memberId);
        commonManager.getStringRedisTemplate().opsForZSet().
                score(RedisCounterMap.MEMBER_ALL_CIRCLE.getValue(), memberId);
        commonManager.getStringRedisTemplate().opsForZSet().
                score(RedisCounterMap.MEMBER_ALL_WORKS.getValue(), memberId);
        counterList = commonManager.getStringRedisTemplate().exec();

        // 从数据库获取并设置到redis
        if (counterList.stream().filter(Objects::nonNull).count() != 4) {
            MessageDto<CounterVo> result = (MessageDto<CounterVo>) pjp.proceed();
            if (!result.getFlag()) {
                return result;
            }

            // 异步执行redis添加操作，无回调
            CompletableFuture.runAsync(() -> {
                commonManager.getStringRedisTemplate().multi();
                commonManager.getStringRedisTemplate().opsForZSet().add(
                        RedisCounterMap.MEMBER_ALL_FANS.getValue(), String.valueOf(memberId), result.getData().getFans());
                commonManager.getStringRedisTemplate().opsForZSet().add(
                        RedisCounterMap.MEMBER_ALL_FAVORATES.getValue(), String.valueOf(memberId), result.getData().getFavorates());
                commonManager.getStringRedisTemplate().opsForZSet().add(
                        RedisCounterMap.MEMBER_ALL_FOLLOW.getValue(), String.valueOf(memberId), result.getData().getFollow());
                commonManager.getStringRedisTemplate().opsForZSet().add(
                        RedisCounterMap.MEMBER_ALL_LIKE.getValue(), String.valueOf(memberId), result.getData().getLike());
                commonManager.getStringRedisTemplate().opsForZSet().add(
                        RedisCounterMap.MEMBER_ALL_CIRCLE.getValue(), String.valueOf(memberId), result.getData().getCircle());
                commonManager.getStringRedisTemplate().opsForZSet().add(
                        RedisCounterMap.MEMBER_ALL_WORKS.getValue(), String.valueOf(memberId), result.getData().getWorks());
                commonManager.getStringRedisTemplate().exec();
                // 按照粉丝数从大到小排序
//                commonManager.getStringRedisTemplate().opsForZSet().
//                        range(RedisCounterMap.MEMBER_ALL_FANS.getValue(), 0, -1)
//                        .stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            }, commonManager.getThreadPoolExecutor());
            return result;
        }
        CounterVo counterVo = new CounterVo();
        counterVo.setFans((Integer) counterList.get(0));
        counterVo.setFavorates((Integer) counterList.get(1));
        counterVo.setFollow((Integer) counterList.get(2));
        counterVo.setLike((Integer) counterList.get(3));
        counterVo.setCircle((Integer) counterList.get(4));
        counterVo.setWorks((Integer) counterList.get(5));
        return new MessageDto<>("getcounterCache success", counterVo, true);
    }

    // 采用失效删除的数据库缓存同步机制
    // 数据库删除或修改数据的同时删除redis相应数据
    @Around(value = "deleteCounterPointCut()&&args(memberId)", argNames = "pjp, memberId")
    public MessageDto<CounterVo> deleteCounterCache(ProceedingJoinPoint pjp, Long memberId) throws Throwable {
        MessageDto<CounterVo> result = (MessageDto<CounterVo>) pjp.proceed();
        if(result.getFlag()) {
            commonManager.getStringRedisTemplate().multi();
            commonManager.getStringRedisTemplate().opsForZSet().remove(CounterMap.FANS.getValue(), memberId);
            commonManager.getStringRedisTemplate().opsForZSet().remove(CounterMap.FAVORATES.getValue(), memberId);
            commonManager.getStringRedisTemplate().opsForZSet().remove(CounterMap.FOLLOW.getValue(), memberId);
            commonManager.getStringRedisTemplate().opsForZSet().remove(CounterMap.LIKE.getValue(), memberId);
            commonManager.getStringRedisTemplate().opsForZSet().remove(CounterMap.CIRCLE.getValue(), memberId);
            commonManager.getStringRedisTemplate().opsForZSet().remove(CounterMap.WORKS.getValue(), memberId);
        }
        return result;
    }
}