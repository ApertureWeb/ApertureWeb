package com.aperture.community.member.common

/**
 * CollectionsFavoratesRela对象的字段映射
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */

public enum CollectionsFavoratesRelaMap {

            FAVORATES_ID ("favorates_id"),
            COLLECTION_ID ("collection_id"),
        ;
    String value;

    public CollectionsFavoratesRelaMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}