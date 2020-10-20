package com.aperture.community.message.test;

import io.vertx.core.Vertx;

public class NormalTest {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        long id=  vertx.setPeriodic(1000,res->{
            System.out.println(res.longValue());


        });
        System.out.println("this is end");
    }
}
