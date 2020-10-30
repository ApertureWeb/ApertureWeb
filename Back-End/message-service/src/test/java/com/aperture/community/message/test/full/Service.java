package com.aperture.community.message.test.full;

import com.alibaba.fastjson.JSON;
import io.vertx.core.*;

import java.util.HashMap;
import java.util.Map;

public class Service {

    private final Vertx vertx;

    public Service(Vertx vertx) {
        this.vertx = vertx;
    }

    public void test() {

    }



        /*
        Future<Map<Integer, String>> future = Future.succeededFuture(new HashMap<>());
        System.out.println(future.onSuccess(x -> {
            vertx.createSharedWorkerExecutor("test").executeBlocking(y -> {
                x.put(1, "1");
            }).compose(y->{
                x.put(2, "2");
                return future;
            }).compose(y->{
                x.put(3, "3");
                return future;
            });
        }).compose(x->{
            x.put(4, "4");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return future;
        }).result());
*/

        /*
        System.out.println(vertx.createSharedWorkerExecutor("test").executeBlocking(x -> {
            future.result().put(3, "3");
        }).compose(x -> {
            future.result().put(1, "1");
            return future;
        }).result());
*/


}





