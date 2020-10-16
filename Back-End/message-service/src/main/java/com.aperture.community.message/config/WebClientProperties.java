package com.aperture.community.message.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("webclient")
@Configuration
@Data
public class WebClientProperties {

    private boolean keepALive;
    private int connectionTimeout;
    private int idleTimeout;
    private int maxWaitQueueSize;
    private int maxPoolSize;

}
