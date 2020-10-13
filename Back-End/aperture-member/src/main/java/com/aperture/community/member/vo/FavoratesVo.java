package com.aperture.community.member.vo;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @Auther: JayV
 * @Date: 2020-10-11 21:29
 * @Description:
 */
public class FavoratesVo {


    /**
     * 收藏夹名称
     */
    private String name;

    /**
     * 用户id
     */
    private Long memberId;
    /**
     * 收藏夹描述
     */
    private String description;


}