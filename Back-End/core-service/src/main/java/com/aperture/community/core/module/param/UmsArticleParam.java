package com.aperture.community.core.module.param;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author HALOXIAO
 * @since 2020-09-23 19:17
 * *
 */

@Getter
@Setter
public class UmsArticleParam {
    private Long id;

    private String title;

    private String content;

    private Integer circleId;

    private List<Integer> tags;

}
