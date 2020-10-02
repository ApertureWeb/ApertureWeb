package com.aperture.community.acl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Auther: JayV
 * @Date: 2020-9-25 14:25
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.aperture.community.acl.client")
public class ApertureAclApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApertureAclApplication.class, args);
    }
}