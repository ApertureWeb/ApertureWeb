package com.aperture.community.standard.code;

/**
 * @author HALOXIAO
 * @since 2020-09-23 19:58
 **/
public enum RESULT_BEAN_STATUS_CODE {

    //未登录
    NO_LOGIN(-1),
    //成功
    SUCCESS(0),
    //未知异常,
    UNKNOWN_EXCEPTION(-99),
    //无权限
    NO_PERMISSION(2002),
    //参数异常,
    ARGUMENT_EXCEPTION(2003),
    REMOTE_ERROR(2004),
    GATEWAY_ERROR(2005),
    REPEAT(2006);

    private final int code;

    RESULT_BEAN_STATUS_CODE(final int code) {
        this.code = code;
    }

    public int getValue() {
        return code;
    }

}
