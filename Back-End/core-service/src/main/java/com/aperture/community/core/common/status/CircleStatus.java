package com.aperture.community.core.common.status;

public enum CircleStatus {

    /**
     * 正常状态
     */
    NORMAL(0),

    /**
     * 审核中：比如初始审核或者因为被举报而进行审核
     */
    REVIEW(1);


    Integer value;

    CircleStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
