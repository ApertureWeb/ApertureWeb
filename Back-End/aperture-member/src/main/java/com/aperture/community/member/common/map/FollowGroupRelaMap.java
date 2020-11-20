package com.aperture.community.member.common.map;

/**
 *
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
public enum FollowGroupRelaMap {

    
        /**
        * id
        */
        ID ("id"),
    
        /**
        * 所属分组id
        */
        GROUP_ID ("group_id"),
    
        /**
        * 当前用户id
        */
        MEMBER_ID ("member_id"),

        GROUP_NAME("group_name"),

        FOLLOW_COUNT("follow_count"),

    IS_DEFAULT("is_default"),

    IS_SPECIAL("is_special");

    String value;

    FollowGroupRelaMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    }
