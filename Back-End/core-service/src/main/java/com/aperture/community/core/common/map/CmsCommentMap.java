package com.aperture.community.core.common.map;

public enum CmsCommentMap {
    ID("id"),
    TARGET_ID("target_id"),
    USER_ID("user_id"),
    CONTENT("content"),
    COMMENT_DATE("comment_date"),
    LIKE("like"),
    STATUS("status");

    String value;

    CmsCommentMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
