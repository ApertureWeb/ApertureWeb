package com.aperture.community.core.test;

import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import com.aperture.community.core.dao.UmsTagMergeMapper;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DiscoveryTest {


    @Autowired
    UmsTagMergeMapper umsTagMergeMapper;

    @Test
    public void content(){

        umsTagMergeMapper.selectById(0);
    }


}
