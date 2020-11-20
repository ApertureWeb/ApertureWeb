package com.aperture.community.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Auther: JayV
 * @Date: 2020-11-14 10:32
 * @Description:
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ApertureAclApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApertureAclApplication.class, args);
    }
}