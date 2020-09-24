package com.aperture.community.core.common;

/**
 * 字段映射
 *
 * @author HALOXIAO
 * @since 2020-09-24 16:22
 **/
public enum UmsTagMap {
    ID("id"),
    NAME("name");

    String value;

    UmsTagMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
