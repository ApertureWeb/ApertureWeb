package com.aperture.community.core.module.param;

import com.aperture.community.core.module.validation.ValidationGroup;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author HALOXIAO
 * @since 2020-09-24 15:23
 **/
@Data
public class CmsTagParam {

    @NotNull(groups = {ValidationGroup.updateGroup.class, ValidationGroup.deleteGroup.class})
    @Null(groups = ValidationGroup.addGroup.class)
    private Long id;
    @NotEmpty(groups = {ValidationGroup.addGroup.class})
    private String name;

    @NotNull(groups = {ValidationGroup.addGroup.class})
    @Null(groups = {ValidationGroup.deleteGroup.class, ValidationGroup.updateGroup.class})
    private Long contentId;

}
