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
@TableName("ums_member_circle_rela")
@ApiModel(value = "MemberCircleRela对象}", description = "comments")
public class MemberCircleRelaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 主键id
         */
    @TableId(value = "id" , type = IdType.NONE)
    @ApiModelProperty(value = "主键id")
    private Long id;
            /**
         * 会员id
         */
    @TableField("memebr_id")
    @ApiModelProperty(value = "会员id")
    private Long memebrId;
            /**
         * 圈子id
         */
    @TableField("circle_id")
    @ApiModelProperty(value = "圈子id")
    private Long circleId;
            /**
         * 职位:0圈友、1管理员、2圈主
         */
    @TableField("position")
    @ApiModelProperty(value = "职位:0圈友、1管理员、2圈主")
    private Integer position;

    
}
