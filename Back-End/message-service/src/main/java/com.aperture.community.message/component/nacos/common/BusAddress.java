package com.aperture.community.message.component.nacos.common;

/**
 * it used to mark bus address
 * @author HALOXIAO
 * @since 2020-10-30 16:26
 **/
public enum BusAddress {

    SERVICE_INFO_INIT_ADDRESS("service_info_init")

    ;

    private String value;

    BusAddress(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
