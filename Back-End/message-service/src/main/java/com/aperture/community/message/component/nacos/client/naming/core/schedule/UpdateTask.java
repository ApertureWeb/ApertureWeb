package com.aperture.community.message.component.nacos.client.naming.core.schedule;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

/**
 * @author HALOXIAO
 * @since 2020-10-28 15:56
 **/
public class UpdateTask extends AbstractVerticle {
    long lastRefTime = Long.MAX_VALUE;

    private final String clusters;

    private final String serviceName;

    public UpdateTask(String serviceName, String clusters) {
        this.serviceName = serviceName;
        this.clusters = clusters;
    }

    @Override
    public void start() throws Exception {
        long delayTime = -1;



    }

}
