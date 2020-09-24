package com.aperture.community.core.common;

/**
 * 字段映射
 *
 * @author HALOXIAO
 * @since 2020-09-24 16:32
 **/
public enum UmsArticleMap {

    ID("id"),
    TITLE("title"),
    CONTENT("content"),
    LIKE("like"),
    COINS("coins"),
    USER_ID("user_uid"),
    CIRCLE_ID("circle_id");

    String value;

    UmsArticleMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
