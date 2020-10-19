package com.aperture.community.message.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("ding")
@Configuration
@Data
public class DingProperties {
    private String host;
    private String token;
}
