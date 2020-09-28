package com.aperture.community.core.common.map;

public enum UmsVideoMap {
    ID("id"),
    TITLE("title"),
    DESCRIPTION("description"),
    LIKE("like"),
    COINS("coins"),
    USER_ID("user_id"),
    CIRCLE_ID("circle_id");
    String value;

    UmsVideoMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
