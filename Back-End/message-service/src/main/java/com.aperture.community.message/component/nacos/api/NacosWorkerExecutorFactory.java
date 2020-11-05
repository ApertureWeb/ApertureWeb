package com.aperture.community.message.component.nacos.api;

import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;

import java.util.concurrent.TimeUnit;

/**
 * @author HALOXIAO
 * @since 2020-10-29 12:10
 **/
public class NacosWorkerExecutorFactory {

    private static Vertx vertx;
    private static WorkerExecutor workerExecutor;
    private static boolean flag = false;
    private static final String defaultName = "NACOS_DEFAULT_WORKER";

    public static void initWorkerExecutor(Vertx vertx) {
        if (!flag) {
            initWorkerExecutor(vertx, defaultName);
        }
    }

    public static void initWorkerExecutor(Vertx vertx, String name) {
        if (!flag) {

            NacosWorkerExecutorFactory.vertx = vertx;
            workerExecutor = vertx.createSharedWorkerExecutor(name);
        }

    }

    public static void initWorkerExecutor(Vertx vertx, String name, int poolSize) {
        if (!flag) {
            NacosWorkerExecutorFactory.vertx = vertx;
            workerExecutor = vertx.createSharedWorkerExecutor(name, poolSize);
        }
    }

    public static void initWorkerExecutor(Vertx vertx, String name, int poolSize, long maxExecuteTime) {
        if (!flag) {
            NacosWorkerExecutorFactory.vertx = vertx;
            workerExecutor = vertx.createSharedWorkerExecutor(name, poolSize, maxExecuteTime);
        }
    }

    public static void initWorkerExecutor(Vertx vertx, String name, int poolSize, long maxExecuteTime, TimeUnit timeUnit) {
        if (!flag) {
            NacosWorkerExecutorFactory.vertx = vertx;
            workerExecutor = vertx.createSharedWorkerExecutor(name, poolSize, maxExecuteTime, timeUnit);
        }
    }


    public static WorkerExecutor getWorkExecutor() {
        if (flag) {
            return workerExecutor;
        } else {
            flag = true;
            return vertx.createSharedWorkerExecutor(defaultName);
        }

    }


}
