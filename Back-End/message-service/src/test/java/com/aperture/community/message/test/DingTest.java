package com.aperture.community.message.test;

import com.alibaba.fastjson.JSON;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import lombok.Data;

import java.util.Map;

public class DingTest {

    private static String dingAddress = "https://oapi.dingtalk.com/robot/send?access_token=67b8829ae802997d67c1a5ec63246e975ea83c3b3b8ce0b333afde235a1bc41c";
    private static String URI = "/robot/send?access_token=67b8829ae802997d67c1a5ec63246e975ea83c3b3b8ce0b333afde235a1bc41c";
    private static String otherAddress = "";
    private static String asd = "https://oapi.dingtalk.com/robot/send";

    public static void main(String[] args) {
        DingAt dingAt = new DingAt();
        dingAt.setAtMobiles(new String[]{"18302033510"});
        Ding ding = new Ding();
        ding.setAt(dingAt);
        ding.setText(Map.of("content", "SverWarnng testing"));
        System.out.println(JSON.toJSONString(ding));
        Vertx vertx = Vertx.vertx();
        WebClient webClient = WebClient.create(vertx);
        webClient.postAbs(asd).addQueryParam("access_token", "67b8829ae802997d67c1a5ec63246e975ea83c3b3b8ce0b333afde235a1bc41c").sendJson(JSON.toJSON(ding), res -> {
            System.out.println("success? :" + res.succeeded() + " result" + res.result().bodyAsString());
        });

    }
}


@Data
class Ding {

    private Map<String, String> text;
    private DingAt at;
    private String msgtype = "text";
}

@Data
class DingAt {
    String[] atMobiles;
    boolean isAtAll = false;
}