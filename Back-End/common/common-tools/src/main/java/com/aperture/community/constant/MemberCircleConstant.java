package com.aperture.community.constant;

/**
 * @Auther: JayV
 * @Date: 2020-10-13 19:23
 * @Description:
 */
public class MemberCircleConstant {

    // 职位:0圈友、1管理员、2圈主
    public enum PositionEnum {
        POSITION_MEMBERS(0, "圈友"),
        POSITION_ADMIN(1, "管理员"),
        POSITION_MASTER(2, "圈主");


        private int code;
        private String msg;

        PositionEnum(int code, String msg) {
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