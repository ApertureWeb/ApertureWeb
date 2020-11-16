package com.aperture.community.core.module.param;

import com.aperture.community.core.module.validation.ReplyValidationGroup;
import com.aperture.community.core.module.validation.ValidationGroup;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author 58471
 * @date 2020/11/16
 */
@Getter
@Setter
public class CmsReplyPageParam extends PageParam {

    @NotNull(groups = {ValidationGroup.addGroup.class})
    private Long commentId;

    @NotNull(groups = {ValidationGroup.addGroup.class, ReplyValidationGroup.InnerReplyGroup.class})
    private Long rootId;

    public CmsReplyPageParam(@Range(min = 1) Integer page, @Range(min = 1, max = 10) Integer size, Long commentId, Long rootId) {
        super(page, size);
        this.commentId = commentId;
        this.rootId = rootId;
    }
}
