package com.aperture.community.message.component.nacos.client.utils;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author HALOXIAO
 * @since 2020-11-01 20:53
 **/
public class JobUtils<T> {

    private int max_attempts = 1;
    private int attempts = 0;
    private final Logger logger = LoggerFactory.getLogger(JobUtils.class);
    private Handler<Promise<T>> handler;
    private T result;

    public Future<JobUtils<T>> attempt(int times, Handler<Promise<T>> handler) {
        this.max_attempts = times;
        this.handler = handler;
        return this.doLog().compose(JobUtils::attemptInternal);
    }


    private Future<JobUtils<T>> doLog(Throwable err) {
        Future<JobUtils<T>> future = Future.succeededFuture();
        if (logger.isDebugEnabled()) {
            logger.debug("retry time :" + attempts, err);
        }
        return future;
    }

    private Future<JobUtils<T>> doLog() {
        Future<JobUtils<T>> future = Future.succeededFuture();
        if (logger.isDebugEnabled()) {
            logger.debug("retry time :" + attempts + ";maxAttempts:" + max_attempts);
        }
        return future;
    }


    private Future<JobUtils<T>> attemptInternal() {
        int remaining = this.max_attempts - this.attempts;
        if (remaining > 0) {
            return this.doAttempt().onFailure(err -> {
                doLog(err);
                attemptInternal();
            }).compose(x -> {
                return Future.succeededFuture(this);
            });
        } else if (remaining == 0) {
            return Future.failedFuture("attempts time done;max try:" + max_attempts);
        } else {
            return Future.failedFuture(new IllegalStateException("Attempts Exceeded"));
        }
    }


    private Future<Object> doAttempt() {
        return Future.future(handler).onFailure(x -> attempts++).compose(res -> {
            result = res;
            return Future.succeededFuture(res);
        });
    }

    public T getResult() {
        return result;
    }


}


