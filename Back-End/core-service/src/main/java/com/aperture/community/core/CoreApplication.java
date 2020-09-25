package com.aperture.community.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author HALOXIAO
 * @since 2020-09-21 14:03
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.aperture.community.core"})
public class CoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class);
    }
}
