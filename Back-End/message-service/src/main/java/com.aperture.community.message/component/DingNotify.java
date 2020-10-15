package com.aperture.community.message.component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.buffer.impl.BufferImpl;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.RequestOptions;
import lombok.AllArgsConstructor;

public class DingNotify extends AbstractVerticle {

    private String message;
    private final String dingAddress = "https://oapi.dingtalk.com/robot/send?access_token=XXXXXX";

    DingNotify(String message) {
        this.message = message;
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        HttpClient httpClient = vertx.createHttpClient();
        MessageText test = new MessageText(new MessageTextContent(message), new MessageTextAt(new String[]{"213123"}, false));
        httpClient.send(new RequestOptions().setMethod(HttpMethod.POST).setHost(dingAddress).setTimeout(4000),
                new BufferImpl().appendString(message), res -> {
                    if (!res.succeeded()) {

//                        TODO 应急处理
                    }
                }
        );

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

