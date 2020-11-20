package com.aperture.community.member.common.constatnt;

/**
 * @Auther: JayV
 * @Date: 2020-10-14 19:58
 * @Description:
 */
public class MemberConstant {

    public enum MemberEnum {
        DONUT_ZERO(0, "1个饼干"),
        FANS_COUNT(0, "0个粉丝"),
        NOT_VIP(0, "不是大会员"),
        BANING(-2, "封号中"),
        LOGOUT(-1, "已注销"),
        NORMAL(0, "用户正常");



        private Integer code;
        private String msg;

        MemberEnum(Integer code, String msg) {
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