package com.aperture.community.member.common.map;

/**
 * 
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
public enum  CollectionMap {

    
        /**
        * 收藏主键id
        */
        ID ("id"),
    
        /**
        * 收藏名
        */
        NAME ("name"),
    
        /**
        * 收藏日期
        */
        COLLECTION_DATE ("collection_date"),
    
        /**
        * 目标作者名字
        */
        AUTHOR_NAME ("author_name"),
    
        /**
        * 收藏目标的id
        */
        TARGET_ID ("target_id"),
    
        /**
        * 所属收藏夹id
        */
        FAVORATES_ID ("favorates_id"),
    
        /**
        * 当前用户id
        */
        MEMBER_ID ("member_id"),
        ;

    String value;

    CollectionMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
