package com.aperture.community.core.common.map;

import javax.swing.*;

/**
 * @author HALOXIAO
 * @since 2020-11-07 21:28
 **/
public enum ServiceNameMap {

    /**
     * 发号器服务
     */
    ID_SERVICE("id-service"),
    ;
    private String value;

    ServiceNameMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
