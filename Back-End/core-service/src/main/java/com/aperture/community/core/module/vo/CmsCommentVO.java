package com.aperture.community.core.module.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CmsCommentVO {

    private Long id;
    private Long targetId;
    private Long rootId;
    private Long replyId;

    private Long userId;
    private String username;

    private String icon;

    private String content;

    private LocalDateTime commentDate;

    private Integer status;

    List<ChildCommentVO> childComment;

}
