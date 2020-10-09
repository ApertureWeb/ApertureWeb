package com.aperture.community.core.manager.cache;

import com.aperture.community.core.common.map.cache.RedisCategoryMap;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.param.CmsCategoryParam;
import com.aperture.community.core.module.vo.CmsCategoryVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.List;

/**
 * @author HALOXIAO
 * @since 2020-10-09 19:15
 **/
@Aspect
@Component
public class CategoryCache {

    private StringRedisTemplate stringRedisTemplate;


    public CategoryCache(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Pointcut(value = "execution(* com.aperture.community.core.service.impl.CmsCategoryServiceImpl.updateCategory(..))")
    public void update() {
    }

    @Pointcut(value = "execution(* com.aperture.community.core.service.impl.CmsCategoryServiceImpl.deleteCategory(..))")
    public void delete() {
    }

    @Pointcut(value = "execution(* com.aperture.community.core.service.impl.CmsCategoryServiceImpl.addCategory(..)))")
    public void add() {
    }

    @Pointcut(value = "execution(* com.aperture.community.core.service.impl.CmsCategoryServiceImpl.list(..))")
    public void getList() {
    }


    @Around(value = "(update()||delete()||add())&&args(*)", argNames = "pjp")
    public MessageDto<Boolean> deleteCache(ProceedingJoinPoint pjp) throws Throwable {
        stringRedisTemplate.delete(RedisCategoryMap.CATEGORY_CACHE);
        MessageDto<Boolean> result = (MessageDto<Boolean>) pjp.proceed();
        if (!result.getData()) {
            return result;
        }
        Thread.sleep(800);
        stringRedisTemplate.delete(RedisCategoryMap.CATEGORY_CACHE);
        return result;
    }

    @Around(value = "getList()", argNames = "pjp")
    public MessageDto<CmsCategoryVO> getCache(ProceedingJoinPoint pjp) {

        return null;
    }


}
