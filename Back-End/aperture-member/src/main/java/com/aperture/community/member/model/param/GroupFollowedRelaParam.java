package com.aperture.community.member.model.param;

import com.aperture.community.member.model.validation.ValidationGroup;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Null;
import java.util.List;


/**
 *
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
@EqualsAndHashCode(callSuper = false)
@Data
public  class GroupFollowedRelaParam {

    @Null(groups = {ValidationGroup.addGroup.class, ValidationGroup.updateGroup.class})
    private Long id;

    @Null(groups = {ValidationGroup.addGroup.class, ValidationGroup.updateGroup.class})
    private Long groupId;

    private Long followedId;

    @Null(groups = {ValidationGroup.addGroup.class, ValidationGroup.updateGroup.class})
    private List<Long> groupIdList;

}