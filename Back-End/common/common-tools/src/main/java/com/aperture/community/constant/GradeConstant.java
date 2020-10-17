package com.aperture.community.constant;

/**
 * @Auther: JayV
 * @Date: 2020-10-13 20:05
 * @Description:
 */
public class GradeConstant {

    public enum GradeEnum {
        GRADE_ONE(1, "1级"),
        GRADE_VALUE_ZERO(0, "经验值为0");


        private Integer code;
        private String msg;

        GradeEnum(Integer code, String msg) {
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