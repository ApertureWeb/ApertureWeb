package com.aperture.community.core.module.param;

import com.aperture.community.core.module.validation.ValidationGroup;
import lombok.Getter;
import lombok.Setter;

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
    @NotNull(groups = {ValidationGroup.deleteGroup.class,ValidationGroup.updateGroup.class})
    private Long id;

    private String title;

    private String content;

    private Integer circleId;

    private List<Integer> tags;

}
