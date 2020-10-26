package com.aperture.community.message.component;

import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class NacosComponentManage implements ApplicationRunner {

    private final Vertx vertx;
    private final RegisterComponent registerComponent;
    private final BeatComponent beatComponent;

    @Autowired
    public NacosComponentManage(Vertx vertx, RegisterComponent registerComponent, BeatComponent beatComponent) {
        this.vertx = vertx;
        this.registerComponent = registerComponent;
        this.beatComponent = beatComponent;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        vertx.deployVerticle(registerComponent);
        vertx.deployVerticle(beatComponent);
    }
}
