package com.aperture.community.message.common;

import lombok.val;

/**
 * @author HALOXIAO
 * @since 2020-10-19 17:10
 **/
public enum ActionMap {

    ;
    String value;

    ActionMap(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
