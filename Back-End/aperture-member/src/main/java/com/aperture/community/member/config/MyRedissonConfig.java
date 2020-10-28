package com.aperture.community.member.config;

import org.mapstruct.BeanMapping;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: JayV
 * @Date: 2020-10-28 12:01
 * @Description:
 */
@Configuration
public class MyRedissonConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() {
        Config config = new Config();
//        config.useClusterServers().addNodeAddress();
        config.useSingleServer().setAddress("redis://192.168.200.128:6379");
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }

}