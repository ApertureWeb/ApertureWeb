package com.aperture.community.member.common

/**
 * MemberLoginLog对象的字段映射
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */

public enum MemberLoginLogMap {

            ID ("id"),
            MEMBER_ID ("member_id"),
            CREATE_TIME ("create_time"),
            IP ("ip"),
            CITY ("city"),
            LOGIN_TYPE ("login_type"),
        ;
    String value;

    public MemberLoginLogMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}