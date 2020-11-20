package com.aperture.community.member.common.map.cache;

/**
 * @Auther: JayV
 * @Date: 2020-11-14 16:50
 * @Description:
 */

public enum RedisAuthenticationMap {

    TOKEN_MAP_KEY("service:member:token");

    String value;

    RedisAuthenticationMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}