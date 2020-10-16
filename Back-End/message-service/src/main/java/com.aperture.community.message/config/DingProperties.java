package com.aperture.community.message.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("ding")
@Configuration
@Data
public class DingProperties {
    private String address;

}
