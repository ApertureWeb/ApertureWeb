package com.aperture.community.member.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: JayV
 * @Date: 2020-11-5 19:55
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "member.thread")
@Data
public class ThreadPoolConfigProperties {

    private Integer coreSize;
    private Integer maxSize;
    private Integer keepAliveTime;
    private Integer LinkedBlockingDeque;

}