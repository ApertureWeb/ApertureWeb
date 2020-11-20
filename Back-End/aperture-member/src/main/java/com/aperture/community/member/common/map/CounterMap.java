package com.aperture.community.member.common.map;

/**
 *
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
public enum  CounterMap {

        /**
        * 
        */
        MEMBER_ID ("member_id"),

        FOLLOW("follow"),

        /**
        * 粉丝数
        */
        FANS ("fans"),
        /**
        * 获赞数
        */
        LIKE ("like"),
    
        /**
        * 收藏夹数
        */
        FAVORATES ("favorates"),

        CIRCLE("circle"),

        WORKS("works"),

        GRADE_VALUE("grade_value");

    String value;

    CounterMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
