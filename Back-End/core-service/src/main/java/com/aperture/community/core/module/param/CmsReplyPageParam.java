package com.aperture.community.core.module.param;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author 58471
 * @date 2020/11/16
 */
@Data
public class CmsReplyPageParam extends PageParam {

    @NotNull
    private Long commentId;

    private Long rootId;

    public CmsReplyPageParam(@Range(min = 1) Integer page, @Range(min = 1, max = 10) Integer size,Long commentId ,Long rootId) {
        super(page, size);
        this.commentId = commentId;
        this.rootId = rootId;
    }
}
