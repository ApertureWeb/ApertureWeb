package com.aperture.community.core.manager;

import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import com.aperture.community.core.module.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationManager {

    private RestTemplate restTemplate;

    @Autowired
    public AuthenticationManager(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    public UserDto getUser(){
//        restTemplate.getForObject();
        return null;
    }

}
