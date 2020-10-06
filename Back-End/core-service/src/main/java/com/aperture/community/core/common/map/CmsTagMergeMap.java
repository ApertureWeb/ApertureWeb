package com.aperture.community.core.common.map;

public enum CmsTagMergeMap {

    ID("id"),
    CONTENT_ID("content_id"),
    TAG_ID("tag_id");


    CmsTagMergeMap(String value){
        this.value = value;
    }

    String value;

    public String getValue() {
        return value;
    }
}
