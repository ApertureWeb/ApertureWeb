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
@TableName("ums_follow")
@ApiModel(value = "Follow对象}", description = "")
public class FollowGroupRelaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * id
         */
    @TableId(value = "id" , type = IdType.NONE)
    @ApiModelProperty(value = "id")
    private Long id;
        /**
         * 所属分组id
         */
    @TableField("group_id")
    @ApiModelProperty(value = "当前用户的关注分组id")
    private Long groupId;
            /**
         * 当前用户id
         */
    @TableField("member_id")
    @ApiModelProperty(value = "当前用户id")
    private Long memberId;


    @TableField("group_name")
    @ApiModelProperty(value = "当前用户的关注分组名称")
    private String groupName;

    @TableField("follow_count")
    @ApiModelProperty(value = "该分组下的关注数量")
    private Integer followCount;

    @TableField("is_default")
    @ApiModelProperty(value = "是否为默认分组")
    private Integer isDefault;

    @TableField("is_special")
    @ApiModelProperty(value = "是否是特别关注分组")
    private Integer isSpecial;

}
