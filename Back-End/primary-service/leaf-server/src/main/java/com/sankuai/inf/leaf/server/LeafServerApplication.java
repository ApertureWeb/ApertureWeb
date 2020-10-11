package com.sankuai.inf.leaf.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class LeafServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeafServerApplication.class, args);
    }
}
