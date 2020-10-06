package com.aperture.community.member.common

/**
 * Member对象的字段映射
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */

public enum MemberMap {

            ID ("id"),
            USERNAME ("username"),
            TELEPHONE ("telephone"),
            PASSWORD ("password"),
            NICKNAME ("nickname"),
            GENDER ("gender"),
            BIRTHDAY ("birthday"),
            HEADER ("header"),
            EMAIL ("email"),
            DOUGHNUT ("doughnut"),
            START_TIME ("start_time"),
            INTEGRATION ("integration"),
            UPDATE_TIME ("update_time"),
            ONLINE ("online"),
            DESCRIPTION ("description"),
            PLACE ("place"),
            SIGN ("sign"),
            INTREST ("intrest"),
            STATUS ("status"),
            GRADE_UID ("grade_uid"),
            SIGN_UID ("sign_uid"),
            FOLLOW_COUNT ("follow_count"),
            FANS_COUNT ("fans_count"),
        ;
    String value;

    public MemberMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}