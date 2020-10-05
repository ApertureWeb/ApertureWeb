package com.aperture.community.core.common;

public enum EventStatus {

    VIDEO(0),
    ARTICLE(1);



    Integer value;

    EventStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }


}
