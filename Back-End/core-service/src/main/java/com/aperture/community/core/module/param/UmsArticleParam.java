package com.aperture.community.core.module.param;

import com.aperture.community.core.module.validation.ValidationGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * @author HALOXIAO
 * @since 2020-09-23 19:17
 * *
 */

@Getter
@Setter
public class UmsArticleParam {
    @Null(groups = {ValidationGroup.addGroup.class})
    @NotNull(groups = {ValidationGroup.deleteGroup.class, ValidationGroup.updateGroup.class})
    private Long id;

    @NotEmpty(groups = {ValidationGroup.addGroup.class})
    private String title;

    @NotEmpty(groups = {ValidationGroup.addGroup.class})
    private String content;

    @NotNull(groups = {ValidationGroup.addGroup.class})
    private Long circleId;


    private List<Integer> tags;

}
