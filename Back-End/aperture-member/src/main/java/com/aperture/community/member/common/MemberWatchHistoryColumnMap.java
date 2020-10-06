package com.aperture.community.member.common

/**
 * MemberWatchHistory对象的字段映射
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */

public enum MemberWatchHistoryMap {

            ID ("id"),
            TARGET_ID ("target_id"),
            TARGET_NAME ("target_name"),
            TARGET_TYPE ("target_type"),
            WATCH_WHERE ("watch_where"),
        ;
    String value;

    public MemberWatchHistoryMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}