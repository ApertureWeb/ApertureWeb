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
@TableName("ums_grade")
@ApiModel(value = "Grade对象}", description = "comments")
public class GradeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 等级id
         */
    @TableId(value = "id" , type = IdType.NONE)
    @ApiModelProperty(value = "等级id")
    private Long id;
            /**
         * 当前等级
         */
    @TableField("grade_level")
    @ApiModelProperty(value = "当前等级")
    private Integer gradeLevel;
            /**
         * 等级名称
         */
    @TableField("grade_name")
    @ApiModelProperty(value = "等级名称")
    private String gradeName;
            /**
         * 等级描述
         */
    @TableField("description")
    @ApiModelProperty(value = "等级描述")
    private String description;
            /**
         * 最高等级
         */
    @TableField("top_grade")
    @ApiModelProperty(value = "最高等级")
    private Integer topGrade;

            /**
         * 是否可以开通创建圈子
         */
    @TableField("is_create_circle")
    @ApiModelProperty(value = "是否可以开通创建圈子")
    private Integer isCreateCircle;
            /**
         * 是否有会员价格优惠特权
         */
    @TableField("is_vip_discount")
    @ApiModelProperty(value = "是否有会员价格优惠特权")
    private Integer isVipDiscount;
            /**
         * 当前等级升级需要的经验
         */
    @TableField("upgrade_value")
    @ApiModelProperty(value = "当前等级升级需要的经验")
    private Integer upgradeValue;
            /**
         * 签到增加的经验
         */
    @TableField("sign_in_growth_value")
    @ApiModelProperty(value = "签到增加的经验")
    private Integer signInGrowthValue;
            /**
         * 评论增加的经验
         */
    @TableField("comment_growth_value")
    @ApiModelProperty(value = "评论增加的经验")
    private Integer commentGrowthValue;
            /**
         * 发布作品增加的经验
         */
    @TableField("publish_growth_value")
    @ApiModelProperty(value = "发布作品增加的经验")
    private Integer publishGrowthValue;
            /**
         * 在线10分钟增加的经验
         */
    @TableField("online_ten_value")
    @ApiModelProperty(value = "在线10分钟增加的经验")
    private Integer onlineTenValue;
            /**
         * 在线30分钟增加的经验
         */
    @TableField("online_thirty_value")
    @ApiModelProperty(value = "在线30分钟增加的经验")
    private Integer onlineThirtyValue;
            /**
         * 在线60分钟增加的经验
         */
    @TableField("online_sixty_value")
    @ApiModelProperty(value = "在线60分钟增加的经验")
    private Integer onlineSixtyValue;
            /**
         * 当前等级创建圈子限制次数
         */
    @TableField("create_circle_limit")
    @ApiModelProperty(value = "当前等级创建圈子限制次数")
    private Integer createCircleLimit;
    
}
