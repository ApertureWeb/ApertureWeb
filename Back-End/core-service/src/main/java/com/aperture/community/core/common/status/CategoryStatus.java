package com.aperture.community.core.common.status;

public enum CategoryStatus {
    /**
     * 显示
     */
    DISPLAY(0),
    /**
     * 不显示
     */
    NO_DISPLAY(1),

    FIRST_LEVEL(1),

    SECOND_LEVEL(2);

    Integer value;

    CategoryStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
