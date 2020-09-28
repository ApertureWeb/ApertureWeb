package com.aperture.community.core.common.map;

public enum  UmsTagMergeMap {

    ID("id"),
    CONTENT_ID("content_id"),
    TAG_ID("tag_id");


    UmsTagMergeMap(String value){
        this.value = value;
    }

    String value;

    public String getValue() {
        return value;
    }
}
