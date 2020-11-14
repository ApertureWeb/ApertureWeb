package com.aperture.community.core.common;

import com.aperture.community.core.module.identify.UserInfo;

/**
 * 有内存泄漏的风险
 *
 * @author HALOXIAO
 * @since 2020-11-07 21:34
 **/
public class TokenStore {
    //TODO perfect it
    private static final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>();

    public static UserInfo getUserInfo() {
        return userInfoThreadLocal.get();
    }

    public static ThreadLocal<UserInfo> getUserInfoThreadLocal() {
        return userInfoThreadLocal;
    }

    public static void cleanUserInfo() {
        userInfoThreadLocal.remove();
    }


}
