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
@TableName("ums_counter")
@ApiModel(value = "Counter对象}", description = "comments")
public class CounterEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    @TableId(value = "member_id" , type = IdType.NONE)
    @ApiModelProperty(value = "用户ID")
    private Integer memberId;

    /**
     * 粉丝数
     */
    @TableField("fans")
    @ApiModelProperty(value = "粉丝数")
    private Integer fans;

    /**
     * 获赞数
     */
    @TableField("like")
    @ApiModelProperty(value = "获赞数")
    private Integer like;

    /**
     * 收藏夹数
     */
    @TableField("favorates")
    @ApiModelProperty(value = "收藏夹数")
    private Integer favorates;

    @TableField("circle")
    private Integer circle;

    @TableField("works")
    private Integer works;

    @TableField("grade_value")
    private Integer gradeValue;
}
