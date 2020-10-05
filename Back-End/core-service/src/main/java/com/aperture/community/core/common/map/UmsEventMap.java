package com.aperture.community.core.common.map;


public enum UmsEventMap {
    CONTENT_ID("content_id"),
    LIKE("like"),
    DONUT("donut"),
    STORE("store"),
    TYPE("type");
    private String value;


    UmsEventMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
