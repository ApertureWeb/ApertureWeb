package com.aperture.community.member.common

/**
 * MemberWatchHistoryRela对象的字段映射
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */

public enum MemberWatchHistoryRelaMap {

            MEMBER_ID ("member_id"),
            HISTORY_ID ("history_id"),
        ;
    String value;

    public MemberWatchHistoryRelaMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}