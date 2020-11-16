package com.aperture.community.core.common.map;

public enum CmsReplyMap {

    ID("id"),
    COMMENT_ID("comment_id"),
    ROOT_ID("root_id"),
    CONTENT("content"),
    USER_ID("user_id"),
    COMMENT_DATE("comment_date"),
    LIKE("like"),
    STATUS("status"),
    ;


    private String value;

    CmsReplyMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
