package com.aperture.community.message.component;

import io.vertx.core.AbstractVerticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeatComponent extends AbstractVerticle {

    private final NacosClient nacosClient;

    @Autowired
    public BeatComponent(NacosClient nacosClient) {
        this.nacosClient = nacosClient;
    }

    public void sendBeat() {
        vertx.setPeriodic(5000, res -> {
            nacosClient.sendBeat(false);
        });
    }


}
