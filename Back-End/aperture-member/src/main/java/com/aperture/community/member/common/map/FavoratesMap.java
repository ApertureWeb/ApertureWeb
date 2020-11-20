package com.aperture.community.member.common.map;

/**
 *
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
public enum  FavoratesMap {

    
        /**
        * 收藏夹id
        */
        ID ("id"),
    
        /**
        * 收藏夹名称
        */
        NAME ("name"),
    
        /**
        * 用户id
        */
        MEMBER_ID ("member_id"),
    
        /**
        * 收藏夹描述
        */
        DESCRIPTION ("description"),
    
        /**
        * 是否公开
        */
        IS_PUBLIC ("is_public"),

        COLLECTION_COUNT("collection_count");

    String value;

    FavoratesMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    }
