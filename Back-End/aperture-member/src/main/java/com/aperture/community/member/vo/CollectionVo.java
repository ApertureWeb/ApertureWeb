package com.aperture.community.member.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: JayV
 * @Date: 2020-10-15 19:14
 * @Description:
 */
@Data
public class CollectionVo {

    private Long id;
    /**
     * 所属收藏夹id
     */
    private Long favoratesId;

}