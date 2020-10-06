package com.aperture.community.core.common.status;

/**
 * @author HALOXIAO
 * @since 2020-10-06 14:49
 **/
public enum ContentStatus {
    /**
     * 正常状态
     */
    NORMAL(0),
    /**
     * 审核状态
     */
    REVIEW(1),

    /**
     * 草稿（只有Article有这个功能）
     */
    DRAFT(2);


    Integer value;

    ContentStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }


}
