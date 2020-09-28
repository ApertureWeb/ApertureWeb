package com.aperture.community.core.module.param;

import com.aperture.community.core.module.validation.ValidationGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Validation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
public class UmsCommentParam {

    @NotNull(groups = {ValidationGroup.deleteGroup.class,ValidationGroup.searchGroup.class})
    @Null(groups = ValidationGroup.addGroup.class)
    private Long id;


    private Long targetId;

    private Long rootId;

    private Long replyId;

    private String content;

}
