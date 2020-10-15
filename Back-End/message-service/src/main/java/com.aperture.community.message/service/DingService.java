package com.aperture.community.message.service;

import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import org.springframework.stereotype.Service;

/**
 * @author HALOXIAO
 * @since 2020-10-15 21:04
 **/
@Service
public class DingService {

    public void sendMessage(String msg) {
        Vertx vertx;
        Vertx.vertx();
    }

}
