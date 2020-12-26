package com.aperture.community.member.common.map;

public enum PrimaryIdMap {

    /**
     * 发号器服务
     */
    ID_SERVICE("id-service"),
    ;
    private String value;

    PrimaryIdMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}