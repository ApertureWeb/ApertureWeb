package com.aperture.community.core.module.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;


/**
 * @author HALOXIAO
 * @email haloxql@gmail.com
 * @date 2020-10-10 10:39:15
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CmsCircleParam {

    @NotNull
    @Null
    private Long id;

    @NotNull
    private Long categoryId;

    @NotNull
    private String image;

    @NotNull
    private String icon;


}
