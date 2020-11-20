package com.aperture.community.member.common.constatnt;

/**
 * @Auther: JayV
 * @Date: 2020-10-13 21:34
 * @Description:
 */
public class TargetTypeConstant {

    // 职位:0圈友、1管理员、2圈主
    public enum TargetTypeEnum {
        TargetType_VIDEO(0, "视频"),
        TargetType_POST(1, "帖子"),
        TargetType_ARTICLE(2, "文章");

        private int code;
        private String msg;

        TargetTypeEnum(int code, String msg) {
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