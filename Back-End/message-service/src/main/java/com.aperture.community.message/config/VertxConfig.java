package com.aperture.community.message.config;

import com.aperture.community.message.config.properties.WebClientProperties;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(WebClientProperties.class)
public class VertxConfig {

    private final Vertx vertx;

    private WebClientProperties webClientProperties;

    @Autowired
    public VertxConfig(WebClientProperties webClientProperties) {
        this.vertx = Vertx.vertx();
        this.webClientProperties = webClientProperties;

    }

    @Bean
    public Vertx getVertx() {
        return vertx;
    }

    @Bean
    public WebClient getWebClient() {
        WebClientOptions options = new WebClientOptions();
        options.setKeepAlive(webClientProperties.isKeepALive());
        options.setConnectTimeout(webClientProperties.getConnectionTimeout());
        options.setIdleTimeout(webClientProperties.getIdleTimeout());
        options.setMaxPoolSize(webClientProperties.getMaxPoolSize());
        options.setMaxWaitQueueSize(webClientProperties.getMaxWaitQueueSize());
        WebClient client = WebClient.create(vertx, options);
        return client;
    }


}
