package com.aperture.community.message.component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.buffer.impl.BufferImpl;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.RequestOptions;
import io.vertx.ext.web.client.WebClient;
import lombok.AllArgsConstructor;

public class DingNotify  {

    private String message;
    private final String dingAddress = "https://oapi.dingtalk.com/robot/send?access_token=XXXXXX";

    DingNotify(String message) {
        this.message = message;
    }


}


class MessageText {
    private String msgtype = "text";
    private MessageTextContent text;
    private MessageTextAt at;

    public MessageText(MessageTextContent text, MessageTextAt at) {
        this.text = text;
        this.at = at;
    }

}

@AllArgsConstructor
class MessageTextContent {
    private String content;
}

@AllArgsConstructor
class MessageTextAt {
    private String[] atMobiles;
    private boolean isAtAll;
}

