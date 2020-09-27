package com.aperture.community.core.module.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UmsArticleViewVO {
    private Long id;

    private String title;

    private String description;

    private Integer like;

    private Integer coins;

    private Long userId;

    private String username;

    private Long circleId;

    private String circleName;

    private String icon;

}
