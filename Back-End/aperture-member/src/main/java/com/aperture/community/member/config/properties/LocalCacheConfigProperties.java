package com.aperture.community.member.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Auther: JayV
 * @Date: 2020-11-14 17:40
 * @Description:
 */
@Component
@Data
@ConfigurationProperties(prefix = "member.caffeine")
public class LocalCacheConfigProperties {

    private Integer expireAfterWrite;
    private Integer expireAfterAccess;

}