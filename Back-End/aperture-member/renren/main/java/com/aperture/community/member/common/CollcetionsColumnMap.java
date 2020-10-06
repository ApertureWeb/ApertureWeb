package com.aperture.community.member.common

/**
 * Collcetions对象的字段映射
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */

public enum CollcetionsMap {

            ID ("id"),
            NAME ("name"),
            COLLECTION_COUNT ("collection_count"),
            COLLECTION_DATE ("collection_date"),
            WATCH_COUNT ("watch_count"),
            TARGET_UID ("target_uid"),
        ;
    String value;

    public CollcetionsMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}