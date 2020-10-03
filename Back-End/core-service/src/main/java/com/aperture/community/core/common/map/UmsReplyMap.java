package com.aperture.community.core.common.map;

public enum UmsReplyMap {

    ID("id"),
    TARGET_ID("target_id"),
    ROOT_ID("root_id"),
    CONTENT("content"),
    USER_ID("user_id"),
    COMMENT_DATE("comment_date"),
    LIKE("like"),
    STATUS("status"),
    REPLY_ID("reply_id");



private String value;

UmsReplyMap(String value){
    this.value = value;
}

    public String getValue() {
        return value;
    }
}
