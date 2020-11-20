package com.aperture.community.member.model.vo;

import lombok.Data;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-17 20:44
 * @Description:
 */
@Data
public class ArticleWatchHistoryVo {

    private Long id;

    private Long targetId;

    private String targetName;

}