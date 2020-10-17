package com.aperture.community.constant;

/**
 * @Auther: JayV
 * @Date: 2020-10-14 17:28
 * @Description:
 */
public class LoginConstant {

    public enum LoginEnum {
        NOT_LOGIN(0, "已下线"),
        LOGINING(1, "上线中"),
        ONLINETIME_ZERO(0, "在线0分钟"),
        TEN_MINUTES(10, "十分钟"),
        BANTIME_ZERO(0, "封禁0分钟");


        private Integer code;
        private String msg;

        LoginEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }



}