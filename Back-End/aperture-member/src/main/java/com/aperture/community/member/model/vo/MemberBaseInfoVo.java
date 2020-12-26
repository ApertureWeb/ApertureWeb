package com.aperture.community.member.model.vo;

import lombok.Data;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-19 22:10
 * @Description:
 */
@Data
public class MemberBaseInfoVo {

    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private Integer mobile;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别[0：男  1：女]
     */
    private Integer gender;
    /**
     * 头像url
     */
    private String headUrl;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 甜甜圈，类比B站硬币
     */
    private Integer donut;

    private Integer isVip;

    private Integer gradeLevel;


}