package com.aperture.community.message.common;

/**
 * @author HALOXIAO
 * @since 2020-10-19 19:39
 **/
public enum NacosUrlMap {
    DISCOVERY_INSTANCE_REGISTER("/nacos/v1/ns/instance"),
    DISCOVERY_INSTANCE_CANCEL("/nacos/v1/ns/instance"),
    DISCOVERY_INSTANCE_UPDATE("/nacos/v1/ns/instance"),
    DISCOVERY_INSTANCE_LIST("/nacos/v1/ns/instance/list"),
    DISCOVERY_INSTANCE_DETAIL("/nacos/v1/ns/instance"),
    DISCOVERY_INSTANCE_BEAT("/nacos/v1/ns/instance/beat"),
    DISCOVERY_SERVICE_CREATE("/nacos/v1/ns/service"),
    DISCOVERY_SERVICE_DELETE("/nacos/v1/ns/service"),
    DISCOVERY_SERVICE_UPDATE("/nacos/v1/ns/service"),
    DISCOVERY_SERVICE_SEARCH("/nacos/v1/ns/service"),
    DISCOVERY_SERVICE_LIST("/nacos/v1/ns/service/list"),
    DISCOVERY_SWITCH_SEARCH("/nacos/v1/ns/operator/switches"),
    DISCOVERY_SWITCH_UPDATE("/nacos/v1/ns/operator/switches"),
    DISCOVERY_METRICS_SEARCH("/nacos/v1/ns/operator/metrics"),
    DISCOVERY_METRICS_LIST("/nacos/v1/ns/operator/servers"),
    DISCOVERY_RAFT_LEADER("/nacos/v1/ns/raft/leader"),
    DISCOVERY_INSTANCE_HEALTH("/nacos/v1/ns/health/instance");


    private String value;

    NacosUrlMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
