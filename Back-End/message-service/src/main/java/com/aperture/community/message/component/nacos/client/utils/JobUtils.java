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
import java.util.Iterator;

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
    private Iterator<T> iterator = null;
    private T result;

    public Future<JobUtils<T>> attempt(int times, Handler<Promise<T>> handler) {
        this.max_attempts = times;
        this.handler = handler;
        return this.doLog().compose(JobUtils::attemptInternal);
    }

    public Future<JobUtils<T>> attemptByParam(Handler<Promise<T>> handler, Collection<T> collection) {
        this.handler = handler;
        this.iterator = collection.iterator();
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
        T data = iterator.next();
        return this.doAttempt().onFailure(err -> {
            doLog(err);
            attemptInternalParam();
        }).compose(x -> {
            return Future.succeededFuture(this);
        });

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
        return Future.future(handler).onFailure(x -> attempts++).compose(res->{
            result = res;
            return Future.succeededFuture(res);
        });
    }

    public T getResult(){
        return result;
    }


}


