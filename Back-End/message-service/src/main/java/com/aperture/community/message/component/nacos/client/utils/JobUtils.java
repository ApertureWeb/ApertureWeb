package com.aperture.community.message.component.nacos.client.utils;

import com.aperture.community.message.component.nacos.api.WebClientFactory;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author HALOXIAO
 * @since 2020-11-01 20:53
 **/
public class JobUtils<R, P> {

    private int max_attempts = 1;
    private int attempts = 0;
    private Vertx vertx;
    private WebClient webClient;
    private final Logger logger = LoggerFactory.getLogger(JobUtils.class);
    private Handler<Promise<P>> handler;
    private boolean stop = false;

    public JobUtils(Vertx vertx, int times) {
        this.vertx = vertx;
        this.webClient = WebClientFactory.getWebClient();
        this.max_attempts = times;
    }

    public Future<Object> attemptWithReturn(Handler<Promise<P>> handler) {
        this.handler = handler;
        return this.doLog()
                .compose(x -> {
                    return this.attemptWithReturn(handler).compose(y -> {

                    });
                });
    }

    public Future<JobUtils<R, P>> attempt(Handler<Promise<P>> handler) {

        return null;
    }

    public Future<JobUtils<R, P>> doLog() {
        Future<JobUtils<R, P>> future = Future.succeededFuture();
        if (logger.isDebugEnabled()) {
            logger.debug("retry time :" + attempts);
        }
        return future;
    }

    private Future<JobUtils<R, P>> error() {

        return null;
    }

    private Future<Object> attemptInternalWithReturn() {
        int remaining = this.max_attempts - this.attempts;
        if (remaining > 0) {
            return this.doAttempt().onFailure(err -> {
                if (logger.isDebugEnabled()) {
                    logger.debug("retry times:" + this.attempts);
                }
            });
        } else if (remaining == 0) {
            this.stop = true;
            return Future.failedFuture("attempts time done");
        } else {
            return Future.failedFuture(new IllegalStateException("Attempts Exceeded"));
        }
    }

    private Future<JobUtils<R, P>> attemptInternal() {
        int remaining = this.max_attempts - this.attempts;
        if (remaining > 0) {
            return null;
        } else if (remaining == 0) {
            return Future.failedFuture("attempts time done");
        } else {
            return Future.failedFuture(new IllegalStateException("Attempts Exceeded"));
        }

    }

    private Future<Object> doAttempt() {
        return Future.future(handler).onFailure(x -> attempts++).compose(Future::succeededFuture);

    }


}
