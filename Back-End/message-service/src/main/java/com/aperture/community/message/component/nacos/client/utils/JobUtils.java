package com.aperture.community.message.component.nacos.client.utils;

import com.aperture.community.message.component.nacos.api.WebClientFactory;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * @author HALOXIAO
 * @since 2020-11-01 20:53
 **/
public class JobUtils<T> {

    private int max_attempts = 1;
    private int attempts = 0;
    private final Logger logger = LoggerFactory.getLogger(JobUtils.class);
    private Handler<Promise<T>> handler;
    private Collection<T> collection = null;

    public Future<JobUtils<T>> attempt(int times, Handler<Promise<T>> handler) {
        this.max_attempts = times;
        this.handler = handler;
        return this.doLog().compose(JobUtils::attemptInternal);
    }

    public Future<JobUtils<T>> attemptByParam(Handler<Promise<T>> handler, Collection<T> collection) {
        this.handler = handler;
        this.collection = collection;
        return this.doLog().compose(JobUtils::attemptInternalParam);
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

    private Future<JobUtils<T>> attemptInternalParam() {


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


    private Future<JobUtils<T>> attemptInternal() {
        collection.iterator();
        collection.forEach(s -> {

        });
        this.doAttempt().onFailure(err -> {

        });
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

        return Future.future(handler).onFailure(x -> attempts++).compose(Future::succeededFuture);

    }


}


