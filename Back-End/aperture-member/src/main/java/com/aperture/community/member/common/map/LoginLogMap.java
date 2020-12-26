package com.aperture.community.member.common.map;

/**
 *
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
public enum  LoginLogMap {

    
        /**
        * 登录日志id
        */
        ID ("id"),
    
        /**
        * 用户id
        */
        MEMBER_ID ("member_id"),
    
        /**
        * 用户登录时的ip
        */
        IP ("ip"),
    
        /**
        * 用户登录时所在的城市
        */
        CITY ("city"),
    
        /**
        * 登录类型[1-web，2-app]
        */
        LOGIN_TYPE ("login_type"),
    
        /**
        * 在线时长(分钟)
        */
        ONLINE_TIME ("online_time"),
    
        /**
        * 注册时间
        */
        REGIST_TIME ("regist_time"),
    
        /**
        * 0：已下线  1：上线中
        */
        LOGIN_STATUS ("login_status"),
    
        /**
        * 封号时长，单位：分钟
        */
        BAN_TIME ("ban_time"),
        ;

    String value;

    LoginLogMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    }
