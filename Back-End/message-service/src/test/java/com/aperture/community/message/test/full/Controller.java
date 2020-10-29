package com.aperture.community.message.test.full;

import io.vertx.core.Vertx;

public class Controller {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        Service s = new Service(vertx);
//        System.out.println(s.test().result());
        s.test();
    }


}
