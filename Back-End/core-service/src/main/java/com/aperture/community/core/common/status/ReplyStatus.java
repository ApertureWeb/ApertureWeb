package com.aperture.community.core.common.status;

/**
 * @author 58471
 * @date 2020/11/16
 */
public enum ReplyStatus {
    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 回复被折叠
     */
    FLOD(1),

    /**
     * 审核状态
     */
    REVIEW(2);

    ReplyStatus(Integer value) {
        this.value = value;
    }

    private Integer value;

    public Integer getValue() {
        return value;
    }
}
