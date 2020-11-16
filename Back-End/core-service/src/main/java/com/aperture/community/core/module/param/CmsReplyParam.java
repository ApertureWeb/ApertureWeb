package com.aperture.community.core.module.param;

import com.aperture.community.core.module.validation.ValidationGroup;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author HALOXIAO
 * @since 2020-10-04 13:48
 **/
@Data
public class CmsReplyParam {

    @Null(groups = {ValidationGroup.addGroup.class})
    @NotNull(groups = {ValidationGroup.deleteGroup.class, ValidationGroup.updateGroup.class, ValidationGroup.searchGroup.class})
    private Long id;

    @NotEmpty(groups = {ValidationGroup.addGroup.class})
    private String content;

    @NotNull
    private Long commentId;

    @NotNull
    private Long rootId;


}
