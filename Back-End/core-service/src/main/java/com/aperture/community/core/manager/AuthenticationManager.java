package com.aperture.community.core.manager;

import com.aperture.community.core.common.map.RedisAuthenticationMap;
import com.aperture.community.core.module.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthenticationManager {

    private RestTemplate restTemplate;
    private StringRedisTemplate authentications;

    @Autowired
    public AuthenticationManager(RestTemplate restTemplate, StringRedisTemplate stringRedisTemplate) {
        this.restTemplate = restTemplate;
        this.authentications = stringRedisTemplate;
    }

    public UserDto getUser(String token) {
        Object infor = authentications.boundHashOps(RedisAuthenticationMap.TOKEN_MAP_KEY.getValue()).get(token);

        return null;
    }

    public void setUser() {
        restTemplate.getForEntity("", UserDto.class);


    }

}