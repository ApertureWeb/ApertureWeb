package com.aperture.community.message.common;



/**
 * @author HALOXIAO
 * @since 2020-10-16 21:19
 **/
public enum MqMap {
    NOTIFY_GROUP("notify"),
    NOTIFY_TOPIC("notify"),
    FEED_TOPIC("feed"),

    EVENT_GROUP("event"),
    EVENT_TOPIC("evnet"),

    ;

    private String value;

    MqMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}