package com.aperture.community.message.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

/**
 * @author HALOXIAO
 * @since 2020-10-16 20:05
 **/
public class JacksonConfig {

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }


}
