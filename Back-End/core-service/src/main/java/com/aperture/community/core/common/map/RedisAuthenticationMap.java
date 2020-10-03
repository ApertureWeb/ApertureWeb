package com.aperture.community.core.common.map;

public enum RedisAuthenticationMap {
    /**
     * 用于存放Token,为Map形式
     */
    TOKEN_MAP_KEY("service:core:token");

    String value;

    RedisAuthenticationMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
