package com.aperture.community.core.manager;

import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    @Autowired
    NacosServiceDiscovery serviceDiscovery;

    public void asd(){

    }

}
