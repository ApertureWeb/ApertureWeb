package com.aperture.community.core.module.param;

import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Range;


public class CmsCommentPageParam extends PageParam {

    Integer articleId;


    public CmsCommentPageParam(@Range(min = 1) Integer page, @Range(min = 1, max = 10) Integer size, Integer articleId) {
        super(page, size);
        this.articleId = articleId;
    }
}
