package com.aperture.community.member.common.constatnt;

/**
 * @Auther: JayV
 * @Date: 2020-10-13 20:05
 * @Description:
 */
public class FavoratesConstant {

    public enum FavoratesEnum {
        DEFAULT_FAVORATES(0, "默认收藏夹"),
        NOT_DEFAULT_FAVORATES(1, "非默认收藏夹"),
        PUBLIC(1, "公开"),
        DEFAULT_DESCRIPTION(0, ""),
        COLLECTION_ZERO(0, "收藏数为0");


        private Integer code;
        private String msg;

        FavoratesEnum(Integer code, String msg) {
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