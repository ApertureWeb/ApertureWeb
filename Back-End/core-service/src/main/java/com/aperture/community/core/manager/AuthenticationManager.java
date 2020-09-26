package com.aperture.community.core.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author HALOXIAO
 * @since 2020-09-26 15:03
 **/
@Service
public class AuthenticationManager {

    private RestTemplate restTemplate;
    private ApplicationContext applicationContext;

    @Autowired
    public AuthenticationManager(RestTemplate restTemplate,ApplicationContext applicationContext) {
        this.restTemplate = restTemplate;
        this.applicationContext = applicationContext;
    }





}
