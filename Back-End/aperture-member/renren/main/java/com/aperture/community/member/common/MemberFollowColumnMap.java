package com.aperture.community.member.common

/**
 * MemberFollow对象的字段映射
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */

public enum MemberFollowMap {

            MEMBER_ID ("member_id"),
            FOLLOW_UID ("follow_uid"),
        ;
    String value;

    public MemberFollowMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}