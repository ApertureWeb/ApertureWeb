package com.aperture.community.acl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: JayV
 * @Date: 2020-10-9 13:11
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApertureAclApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApertureAclApplication.class, args);
    }
}