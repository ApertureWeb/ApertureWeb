package com.aperture.community.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author HALOXIAO
 * @since 2020-10-16 20:33
 **/
@SpringBootApplication
@EnableAsync
public class MessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class);
    }

}