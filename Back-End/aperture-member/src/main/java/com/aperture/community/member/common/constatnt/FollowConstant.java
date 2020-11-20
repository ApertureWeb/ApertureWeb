package com.aperture.community.member.common.constatnt;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-18 21:10
 * @Description:
 */
public enum FollowConstant {

    IS_DEFAULT_GROUP(String.valueOf(1)),
    IS_SPECIAL_GROUP(String.valueOf(1));

    String value;

    FollowConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}