package com.aperture.community.core.module.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChildCommentVO {

    Long id;

    Long userId;

    String username;

    String content;

    Integer like;

    LocalDateTime commentDate;

}
