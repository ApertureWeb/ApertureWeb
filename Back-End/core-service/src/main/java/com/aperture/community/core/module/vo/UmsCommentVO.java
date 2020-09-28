package com.aperture.community.core.module.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UmsCommentVO {

    private Long id;

    private Long targetId;

    private Long rootId;

    private Long replyId;

    private Long userId;

    private String content;

    private LocalDateTime commentDate;

    private Integer status;


}
