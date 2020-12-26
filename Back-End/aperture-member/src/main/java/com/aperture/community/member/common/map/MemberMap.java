package com.aperture.community.member.common.map;

/**
 *
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
public enum  MemberMap {

    
        /**
        * 用户id
        */
        ID ("id"),
    
        /**
        * 用户名
        */
        USERNAME ("username"),
    
        /**
        * 手机号
        */
        MOBILE ("mobile"),
    
        /**
        * 密码
        */
        PASSWORD ("password"),
    
        /**
        * 昵称
        */
        NICKNAME ("nickname"),
    
        /**
        * 性别[0：男  1：女]
        */
        GENDER ("gender"),
    
        /**
        * 生日
        */
        BIRTHDAY ("birthday"),
    
        /**
        * 头像url
        */
        HEAD_URL ("head_url"),
    
        /**
        * 邮箱
        */
        EMAIL ("email"),
    
        /**
        * 甜甜圈，类比B站硬币
        */
        DONUT ("donut"),
    
        /**
        * 会员积分
        */
        MEMBER_POINT ("member_point"),
    
        /**
        * 信息修改日期
        */
        UPDATE_TIME ("update_time"),
    
        /**
        * 认证信息/交友宣言
        */
        DESCRIPTION ("description"),
    
        /**
        * 所在地
        */
        PLACE ("place"),
    
        /**
        * 是否开通了会员
        */
        IS_VIP ("is_vip"),
    
        /**
        * 个性签名
        */
        SIGN ("sign"),
    
        /**
        * 爱好
        */
        INTEREST ("interest"),
    
        /**
        * 等级
        */
        GRADE_LEVEL ("grade_level"),
    
        /**
        * 是否实名认证
        */
        IS_CERTIFICATED ("is_certificated"),
    
        /**
        * -2：封号中  -1：已注销  0：正常
        */
        MEMBER_STATUS ("member_status"),
        ;

    String value;

    MemberMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    }
