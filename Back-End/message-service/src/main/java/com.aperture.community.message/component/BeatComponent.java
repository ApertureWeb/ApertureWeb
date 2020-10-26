package com.aperture.community.message.component;

import com.aperture.community.message.config.properties.NacosProperties;
import io.vertx.core.AbstractVerticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeatComponent extends AbstractVerticle {

    private final NacosClient nacosClient;
    private final NacosProperties properties;

    @Autowired
    public BeatComponent(NacosClient nacosClient, NacosProperties properties) {
        this.nacosClient = nacosClient;
        this.properties = properties;
    }

    public void sendBeat() {
        vertx.setPeriodic(properties.getBeatIntervalTime(), res -> {
            nacosClient.sendBeat(false);
        });
    }


}
