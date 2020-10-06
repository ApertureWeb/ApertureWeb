package com.aperture.community.core.common.map;

/**
 * 字段映射
 *
 * @author HALOXIAO
 * @since 2020-09-24 16:22
 **/
public enum CmsTagMap {
    ID("id"),
    NAME("name");

    String value;

    CmsTagMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
