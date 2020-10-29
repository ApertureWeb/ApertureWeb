package com.aperture.community.message.test.full;

import com.google.common.collect.HashBiMap;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

import java.util.HashMap;
import java.util.Map;

public class Service {

    private final Vertx vertx;

    public Service(Vertx vertx) {
        this.vertx = vertx;
    }

    public Map<Integer,String> test() {
        Map<Integer,String> flag = new HashMap<>();
        vertx.createSharedWorkerExecutor("asd").executeBlocking(exe -> {
                    try {
                        Thread.sleep(1000);
                        flag.put(1, "1");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        return flag;
    }



}



