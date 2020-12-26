package com.aperture.community.member.common.map;

/**
 *
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
public enum GroupFollowedRelaMap {

    
        /**
        * 关注分组id
        */
        ID ("id"),
    
        /**
        * 分组名
        */
        GROUP_ID ("group_id"),

    
        /**
        * 该分组所属用户id
        */
        FOLLOWED_ID ("followed_id");

    String value;

    GroupFollowedRelaMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    }
