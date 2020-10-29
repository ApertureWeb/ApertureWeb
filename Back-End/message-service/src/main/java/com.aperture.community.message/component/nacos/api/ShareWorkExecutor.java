package com.aperture.community.message.component.nacos.api;

import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;

import java.util.concurrent.TimeUnit;

/**
 * @author HALOXIAO
 * @since 2020-10-29 12:13
 **/
public class ShareWorkExecutor {

    private final Vertx vertx;
    private final WorkerExecutor workerExecutor;
    private static final String DEFAULT_NAME = "Default-Worker";

    /**
     * 其余参数会自动使用VertxOption中的数据
     */
    public ShareWorkExecutor(Vertx vertx) {
        this(vertx, DEFAULT_NAME);
    }

    public ShareWorkExecutor(Vertx vertx, String name) {
        this.vertx = vertx;
        this.workerExecutor = vertx.createSharedWorkerExecutor(name);
    }

    public ShareWorkExecutor(Vertx vertx, String name, int poolSize) {
        this.vertx = vertx;
        this.workerExecutor = vertx.createSharedWorkerExecutor(name, poolSize);
    }

    public ShareWorkExecutor(Vertx vertx, String name, int poolSize, long executeTime, TimeUnit timeUnit) {
        this.vertx = vertx;
        this.workerExecutor = vertx.createSharedWorkerExecutor(name, poolSize, executeTime, timeUnit);
    }

    public WorkerExecutor getWorkerExecutor() {
        return workerExecutor;
    }

}
