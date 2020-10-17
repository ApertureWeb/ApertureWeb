package com.aperture.community.member.vo;

import lombok.Data;

/**
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
@Data
public class FavoratesUpdateVo {

    private Long id;

    private String name;

    private Integer inPublic;  // 1:公开   0：不公开

}