package com.aperture.community.message.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author HALOXIAO
 * @since 2020-10-16 21:01
 **/
@ConfigurationProperties("rocketmq")
@Configuration
@Data
public class RocketMQProperties {

    private String namesrv;


}
