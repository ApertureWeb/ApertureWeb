package com.aperture.community.core.module.param;

import com.aperture.community.core.module.validation.ValidationGroup;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * @author HALOXIAO
 * @since 2020-09-23 17:20
 **/
@Getter
@Setter
@ToString
public class CmsVideoParam {

    @Null(groups = {ValidationGroup.addGroup.class})
    @NotNull(groups = {ValidationGroup.deleteGroup.class,ValidationGroup.updateGroup.class})
    private Integer id;
    private String title;
    private String content;
    private Integer circle;
    private List<Integer> tags;
}