package com.aperture.community.standard.identity;

/**
 * Tag for each service identity
 */
public enum ServiceIdentity {
    /**
     * 主键服务
     */
    PRIMARY_KEY_SERVICE("primary_key-service"),
    CONTENT_SERVICE("Content-Service");
    private String value;

    ServiceIdentity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
