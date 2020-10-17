package com.aperture.community.message.service;

import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HALOXIAO
 * @since 2020-10-16 21:16
 **/
@Service
public class FeedService {

    private final Vertx vertx;

    @Autowired
    public FeedService(Vertx vertx) {
        this.vertx = vertx;
    }



 }
