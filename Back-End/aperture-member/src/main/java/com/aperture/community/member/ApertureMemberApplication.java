package com.aperture.community.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: JayV
 * @Date: 2020-10-3 21:37
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApertureMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApertureMemberApplication.class, args);
    }

}