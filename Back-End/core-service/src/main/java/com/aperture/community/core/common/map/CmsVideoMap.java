package com.aperture.community.core.common.map;

public enum CmsVideoMap {
    ID("id"),
    TITLE("title"),
    DESCRIPTION("description"),
    USER_ID("user_id"),
    CIRCLE_ID("circle_id");
    String value;

    CmsVideoMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
