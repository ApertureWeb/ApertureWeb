package com.aperture.community.message.module.dto;

import com.alibaba.fastjson.JSONObject;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;

/**
 * @author HALOXIAO
 * @since 2020-10-15 21:06
 **/
public class DingDto {


    private String msgtype = "text";
    private MessageTextContent text;
    private MessageTextAt at;

    public DingDto(MessageTextContent text, MessageTextAt at) {
        this.text = text;
        this.at = at;

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

    public String toJsonString(Vertx vertx) {
        JsonObject textObject = new JsonObject();
        textObject.put("content", text.content);
        JSONObject.toJSONString()
        JsonObject mainObject = new JsonObject();
        mainObject.put("msgType", msgtype);
        mainObject.put("text", textObject);

    }

}