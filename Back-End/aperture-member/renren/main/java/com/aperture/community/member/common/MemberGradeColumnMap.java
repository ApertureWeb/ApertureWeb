package com.aperture.community.member.common

/**
 * MemberGrade对象的字段映射
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */

public enum MemberGradeMap {

            ID ("id"),
            NAME ("name"),
            IS_DEFAULT_GRADE ("is_default_grade"),
            GROWTH_VALUE ("growth_value"),
            SIGN_IN_GROWTH_VALUE ("sign_in_growth_value"),
            PUBLISH_GROWTH_VALUE ("publish_growth_value"),
            COMMENT_GROWTH_VALUE ("comment_growth_value"),
            IS_VIP_DISCOUNT ("is_vip_discount"),
            DESCRIPTION ("description"),
        ;
    String value;

    public MemberGradeMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}