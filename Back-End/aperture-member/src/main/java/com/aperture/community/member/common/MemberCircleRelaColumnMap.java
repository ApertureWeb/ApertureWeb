package com.aperture.community.member.common

/**
 * MemberCircleRela对象的字段映射
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */

public enum MemberCircleRelaMap {

            ID ("id"),
            MEMBER_ID ("member_id"),
            CIRCLE_ID ("circle_id"),
            POSITION ("position"),
            STATUS ("status"),
        ;
    String value;

    public MemberCircleRelaMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}