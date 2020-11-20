package com.aperture.community.member.model.vo;

import lombok.Data;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-16 10:28
 * @Description:
 */
@Data
public class CounterVo {


    private Integer follow;
    /**
     * 粉丝数
     */
    private Integer fans;

    /**
     * 获赞数
     */
    private Integer like;

    /**
     * 收藏夹数
     */
    private Integer favorates;

    private Integer circle;

    private Integer works;

    private Integer gradeValue;

}