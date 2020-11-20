package com.aperture.community.member.common.map;

/**
 *
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
public enum  GradeMap {

    
        /**
        * 等级id
        */
        ID ("id"),
    
        /**
        * 当前等级
        */
        GRADE_LEVEL ("grade_level"),
    
        /**
        * 等级名称
        */
        GRADE_NAME ("grade_name"),
    
        /**
        * 等级描述
        */
        DESCRIPTION ("description"),
    
        /**
        * 最高等级
        */
        TOP_GRADE ("top_grade"),
    
        /**
        * 是否为默认等级
        */
        IS_DEFAULT_GRADE ("is_default_grade"),
    
        /**
        * 是否可以开通创建圈子
        */
        IS_CREATE_CIRCLE ("is_create_circle"),
    
        /**
        * 是否有会员价格优惠特权
        */
        IS_VIP_DISCOUNT ("is_vip_discount"),
    
        /**
        * 当前等级升级需要的经验
        */
        GROWTH_VALUE ("growth_value"),
    
        /**
        * 签到增加的经验
        */
        SIGN_IN_GROWTH_VALUE ("sign_in_growth_value"),
    
        /**
        * 评论增加的经验
        */
        COMMENT_GROWTH_VALUE ("comment_growth_value"),
    
        /**
        * 发布作品增加的经验
        */
        PUBLISH_GROWTH_VALUE ("publish_growth_value"),
    
        /**
        * 在线10分钟增加的经验
        */
        ONLINE_TEN_VALUE ("online_ten_value"),
    
        /**
        * 在线30分钟增加的经验
        */
        ONLINE_THIRTY_VALUE ("online_thirty_value"),
    
        /**
        * 在线60分钟增加的经验
        */
        ONLINE_SIXTY_VALUE ("online_sixty_value"),
    
        /**
        * 当前等级创建圈子限制次数
        */
        CREATE_CIRCLE_LIMIT ("create_circle_limit"),
        ;

    String value;

    GradeMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    }
