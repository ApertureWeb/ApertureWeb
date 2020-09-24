package com.aperture.community.common.standard.code;

/**
 * @author HALOXIAO
 * @since 2020-09-24 22:07
 **/
public enum STATUS_CODE {
    NORMAL(0),
    REVIEW(1),
    DELETE(-1),
    DISABLE(-2);

    private Integer code;

    STATUS_CODE(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
