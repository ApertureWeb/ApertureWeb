package com.aperture.community.message.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author HALOXIAO
 * @since 2020-10-16 21:44
 **/
@ConfigurationProperties("mysql")
@Configuration
@Data
public class MySQLProperties {

    private String address;
    private int maxSize;
    private int maxWaitQueueSize;
    private String user;
    private String password;
    private int port;
    private String database;

}
