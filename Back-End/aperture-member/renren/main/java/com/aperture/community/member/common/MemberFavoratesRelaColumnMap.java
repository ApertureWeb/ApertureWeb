package com.aperture.community.member.common

/**
 * MemberFavoratesRela对象的字段映射
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */

public enum MemberFavoratesRelaMap {

            MEMBER_ID ("member_id"),
            FAVORATES_ID ("favorates_id"),
        ;
    String value;

    public MemberFavoratesRelaMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}