package com.aperture.community.core.config;

import com.aliyun.oss.OSSClient;
import com.aperture.community.core.config.properties.OSSProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author HALOXIAO
 * @since 2020-10-17 13:30
 **/
@Configuration
@Import(OSSProperties.class)
public class OSSConfig {

    private final OSSProperties ossProperties;

    @Autowired
    public OSSConfig(OSSProperties ossProperties) {
        this.ossProperties = ossProperties;
    }


    @Bean
    public OSSClient getOSSClient() {

        OSSClient client = new OSSClient(ossProperties.getEndPoint()
                , ossProperties.getAccessKeyId()
                , ossProperties.getAccessKeySecret());
        return client;
    }


}
