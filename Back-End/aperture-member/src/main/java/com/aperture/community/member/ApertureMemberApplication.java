package com.aperture.community.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Auther: JayV
 * @Date: 2020-10-11 13:37
 * @Description:
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.aperture.community.member.dao")
@EnableFeignClients(basePackages = "com.aperture.community.member.feign")
public class ApertureMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApertureMemberApplication.class, args);
    }
}