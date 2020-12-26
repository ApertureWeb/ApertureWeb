package com.aperture.community.member.common.map;

/**
 *
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
public enum  MemberCircleRelaMap {

    
        /**
        * 主键id
        */
        ID ("id"),
    
        /**
        * 会员id
        */
        MEMEBR_ID ("memebr_id"),
    
        /**
        * 圈子id
        */
        CIRCLE_ID ("circle_id"),
    
        /**
        * 职位:0圈友、1管理员、2圈主
        */
        POSITION ("position")


        ;

    String value;

    MemberCircleRelaMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    }
