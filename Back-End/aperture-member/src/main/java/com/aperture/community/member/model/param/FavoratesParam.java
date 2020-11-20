package com.aperture.community.member.model.param;

import com.aperture.community.member.model.validation.ListValue;
import com.aperture.community.member.model.validation.ValidationGroup;
import io.swagger.models.auth.In;
import javafx.scene.input.InputMethodTextRun;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 *
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
@EqualsAndHashCode(callSuper = false)
@Data
public  class  FavoratesParam{

    @Null(groups = ValidationGroup.addGroup.class)
    @NotNull(groups = {ValidationGroup.deleteGroup.class, ValidationGroup.updateGroup.class,ValidationGroup.searchGroup.class})
    private Long id;

    @NotNull(groups = {ValidationGroup.addGroup.class, ValidationGroup.updateGroup.class,ValidationGroup.searchGroup.class})
    @Length(max = 10)
    private String name;

    @NotNull(groups = {ValidationGroup.addGroup.class, ValidationGroup.updateGroup.class, ValidationGroup.deleteGroup.class, ValidationGroup.searchGroup.class})
    @Length(max = 100)
    private String description;

    @NotNull(groups = {ValidationGroup.addGroup.class, ValidationGroup.updateGroup.class, ValidationGroup.deleteGroup.class,ValidationGroup.searchGroup.class})
    private Integer isPublic;

    @NotNull(groups = {ValidationGroup.addGroup.class, ValidationGroup.searchGroup.class})
    private Integer collectionCount;

}