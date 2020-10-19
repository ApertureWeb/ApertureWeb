package com.aperture.community.message.service.listener;

import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

/**
 * @author HALOXIAO
 * @since 2020-10-16 21:16
 **/
@Service
public class FeedListener implements ApplicationRunner {

    private final Vertx vertx;


    @Autowired
    public FeedListener(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//    先查询有哪些人已经上线

    }
}
