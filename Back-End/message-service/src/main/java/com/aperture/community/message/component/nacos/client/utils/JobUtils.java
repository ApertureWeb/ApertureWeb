package com.aperture.community.message.component.nacos.client.utils;

import com.aperture.community.message.component.nacos.api.WebClientFactory;
import io.vertx.core.Future;
import io.vertx.core.Handler;
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

    public JobUtils(Vertx vertx) {
        this.vertx = vertx;
        this.webClient = WebClientFactory.getWebClient();
    }

    public Future<JobUtils<R, P>> attempt(int times, Handler<P> handler) {
        return Future.succeededFuture();

    }

    private Future<JobUtils<R, P>> error() {

    }

    private Future<JobUtils<R, P>> attemptInternal() {
        int remaining = this.max_attempts - this.attempts;
        if (remaining > 0) {
            return this.doAttempt()
        } else if (remaining == 0) {
            return Future.failedFuture("attempts time done");
        } else {
            return Future.failedFuture(new IllegalStateException("Attempts Exceeded"));
        }

    }

    private Future<JobUtils<R, P>> doAttempt() {

    }


}
