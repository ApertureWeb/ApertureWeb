package com.aperture.community.member.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: JayV
 * @Date: 2020-11-15 13:17
 * @Description:
 */
@Data
public class CollectionListVo {

    private Long id;

    private String name;

    private String collectionDate;

    private Long targetId;
}