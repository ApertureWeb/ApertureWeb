package com.aperture.community.message.component.nacos.api;

import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

/**
 * @author HALOXIAO
 * @since 2020-10-30 20:35
 **/
public class WebClientFactory {

    private static WebClient webClient;
    private static Vertx vertx;
    private static WebClientOptions webClientOptions;

    public static void initFactory(Vertx overtx, WebClientOptions options) {
        vertx = overtx;
        webClientOptions = options;
    }

    public static void initFactory(Vertx overtx) {
        vertx = overtx;
        webClientOptions = new WebClientOptions();
    }


    public static WebClient getWebClient() {
        if (webClient == null) {
            synchronized (WebClientFactory.class) {
                if (webClient == null) {
                    webClient = WebClient.create(vertx, webClientOptions);
                }
            }
        }
        return webClient;
    }

}
