package com.aperture.community.member.common

/**
 * Favorates对象的字段映射
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */

public enum FavoratesMap {

            ID ("id"),
            NAME ("name"),
            PLAY_COUNT ("play_count"),
            FAVORATES_COUNT ("favorates_count"),
        ;
    String value;

    public FavoratesMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}