package com.aperture.community.common.standard.identity;

/**
 * Tag for each service identity
 */
public enum ServiceIdentity {
    /**
     * 主键服务
     */
    PRIMARY_KEY_SERVICE("primary_key-service");

    private String value;

    ServiceIdentity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}