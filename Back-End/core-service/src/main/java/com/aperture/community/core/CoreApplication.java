package com.aperture.community.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author HALOXIAO
 * @since 2020-09-21 14:03
 **/
@MapperScan(value = "com.aperture.community.core.dao")
@SpringBootApplication
@EnableDiscoveryClient
@EnableAspectJAutoProxy
@EnableScheduling
@EnableCaching
public class CoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class);
    }
}
