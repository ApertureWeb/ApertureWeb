package com.aperture.community.core.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HALOXIAO
 * @since 2020-09-23 21:37
 **/
@Configuration
public class RestTemplateConfig {

    @LoadBalanced
    @Bean
    public org.springframework.web.client.RestTemplate restTemplate() {
        return new org.springframework.web.client.RestTemplate();
    }

}
