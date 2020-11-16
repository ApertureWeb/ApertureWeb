package com.aperture.community.core.module.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author HALOXIAO
 * @since 2020-10-04 16:43
 **/
@Data
public class CmsReplyVO {

    private Long id;

    private Long userId;

    private String username;

    private String icon;

    private LocalDateTime commentDate;

    private String content;

    private Long rootId;

    private Integer status;

    private Integer like;
}
