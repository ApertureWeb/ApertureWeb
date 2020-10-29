package com.aperture.community.message.component.nacos.api;

import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;

/**
 * @author HALOXIAO
 * @since 2020-10-29 12:10
 **/
public class ShareWorkerExecutorFactory {

    private static Vertx vertx;
    private static WorkerExecutor workerExecutor;
    public static void setVertx(Vertx vertx) {
        ShareWorkerExecutorFactory.vertx = vertx;
    }

    public static WorkerExecutor getWorkExecutor() {
        return workerExecutor;
    }


}
