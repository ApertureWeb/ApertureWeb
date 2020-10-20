package com.aperture.community.message.test;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

public class BusTest {
    BusTest() throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        sender s = new sender(vertx);
        register r = new register(vertx);
        s.send();
        r.receive();
    }

    public static void main(String[] args) throws InterruptedException {
        BusTest test = new BusTest();
    }

}

class sender {
    private Vertx vertx;

    sender(Vertx vertx) {
        this.vertx = vertx;
    }

    public void send() {
        EventBus eventBus = vertx.eventBus();
        DeliveryOptions options = new DeliveryOptions();
        options.setLocalOnly(true);
        System.out.println("event start");
        eventBus.publish("hello", "test",options);
        System.out.println("down");
    }

}

class register {
    private Vertx vertx;

    register(Vertx vertx) {
        this.vertx = vertx;
    }

    public void receive() {
        System.out.println("receive start");
        EventBus eventBus = vertx.eventBus();
        eventBus.consumer("hello", res -> {
            System.out.println("start consumer");
            System.out.println(res.body().toString());
        });
    }


}