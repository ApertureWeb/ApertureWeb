package com.aperture.community.member.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

    import java.util.Date;

/**
 *
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
@Data
@TableName("ums_follow_group")
@ApiModel(value = "FollowGroup对象}", description = "")
public class GroupFollowedRelaEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    @TableId(value = "id" , type = IdType.NONE)
    @ApiModelProperty(value = "id")
    private Long id;

    @TableField("group_id")
    @ApiModelProperty(value = "当前分组id")
    private Long groupId;


    @TableField("followed_id")
    @ApiModelProperty(value = "被关注用户id")
    private Long followedId;

}
