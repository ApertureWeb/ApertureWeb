package com.aperture.community.core.module.vo;

import lombok.Data;

/**
 * @author HALOXIAO
 * @since 2020-09-23 19:16
 **/
@Data
public class UmsArticleVO {

    private Long id;

    private String title;

    private String content;

    private Integer like;

    private Integer coins;

    private Long userId;

    private String username;

    private Long circleId;

    private String circleName;

}
