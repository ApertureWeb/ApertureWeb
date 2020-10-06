package com.aperture.community.core.common.map;


public enum CmsEventMap {
    CONTENT_ID("content_id"),
    LIKE("like"),
    DONUT("donut"),
    STORE("store"),
    TYPE("type");
    private String value;


    CmsEventMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
