package com.aperture.community.message.common;

import io.netty.handler.codec.mqtt.MqttPublishMessage;
import org.bouncycastle.cms.PasswordRecipientId;

/**
 * @author HALOXIAO
 * @since 2020-10-16 21:19
 **/
public enum MqMap {
    NOTIFY_GROUP("notify"),
    NOTIFY_TOPIC("notify"),

    FEED_TOPIC("feed"),

    ;

    private String value;

    MqMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
