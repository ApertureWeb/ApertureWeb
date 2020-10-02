package com.aperture.community.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: JayV
 * @Date: 2020-10-2 20:16
 * @Description:
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class ApertureGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApertureGatewayApplication.class, args);
    }
}