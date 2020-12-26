package com.aperture.community.member.model.param;

import com.aperture.community.member.model.validation.ListValue;
import com.aperture.community.member.model.validation.ValidationGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;


/**
 *
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
@Data
public  class  CollectionParam{

    @Null(groups = ValidationGroup.addGroup.class)
    @NotNull(groups = {ValidationGroup.deleteGroup.class, ValidationGroup.updateGroup.class,ValidationGroup.searchGroup.class})
    private Long id;

    /**
     * 收藏名
     */
    @NotNull(groups = {ValidationGroup.addGroup.class, ValidationGroup.updateGroup.class,ValidationGroup.searchGroup.class})
    @Length(min = 1, max = 10)
    private String name;

    /**
     * 收藏目标的id
     */
    @NotNull(groups = {ValidationGroup.addGroup.class, ValidationGroup.deleteGroup.class, ValidationGroup.updateGroup.class,ValidationGroup.searchGroup.class})
    private Long targetId;

    /**
     * 所属收藏夹id
     */
    @NotNull(groups = {ValidationGroup.addGroup.class, ValidationGroup.deleteGroup.class, ValidationGroup.updateGroup.class,ValidationGroup.searchGroup.class})
    private Long favoratesId;
    /**
     * 当前用户id
     */
    @NotNull(groups = {ValidationGroup.addGroup.class, ValidationGroup.updateGroup.class,ValidationGroup.searchGroup.class})
    private Long memberId;

}