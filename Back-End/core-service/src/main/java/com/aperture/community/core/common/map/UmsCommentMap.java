package com.aperture.community.core.common.map;

public enum  UmsCommentMap {
    ID("id"),
    TARGET_ID("target_id"),
    ROOT_ID("root_id"),
    REPLY_ID("reply_id"),
    USER_ID("user_id"),
    CONTENT("content"),
    COMMENT_DATE("comment_date"),
    LIKE("like"),
    STATUS("status");

    String value;

    UmsCommentMap(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
