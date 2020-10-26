package com.aperture.community.message.component.nacos.client.net;

import com.aperture.community.message.component.nacos.common.HttpMethod;
import io.vertx.core.Vertx;

public class HttpClient {

    private final Vertx vertx;


    public HttpClient(Vertx vertx) {
        this.vertx = vertx;
    }


    public void execute(String uri, HttpMethod httpMethod) {

    }

}
