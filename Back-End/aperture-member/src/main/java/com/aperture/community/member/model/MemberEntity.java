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
@TableName("ums_member")
@ApiModel(value = "Member对象}", description = "comments")
public class MemberEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 用户id
         */
    @TableId(value = "id" , type = IdType.NONE)
    @ApiModelProperty(value = "用户id")
    private Long id;
            /**
         * 用户名
         */
    @TableField("username")
    @ApiModelProperty(value = "用户名")
    private String username;
            /**
         * 手机号
         */
    @TableField("mobile")
    @ApiModelProperty(value = "手机号")
    private Integer mobile;
            /**
         * 密码
         */
    @TableField("password")
    @ApiModelProperty(value = "密码")
    private String password;
            /**
         * 昵称
         */
    @TableField("nickname")
    @ApiModelProperty(value = "昵称")
    private String nickname;
            /**
         * 性别[0：男  1：女]
         */
    @TableField("gender")
    @ApiModelProperty(value = "性别[0：男  1：女]")
    private Integer gender;
            /**
         * 生日
         */
    @TableField("birthday")
    @ApiModelProperty(value = "生日")
    private String birthday;
            /**
         * 头像url
         */
    @TableField("head_url")
    @ApiModelProperty(value = "头像url")
    private String headUrl;
            /**
         * 邮箱
         */
    @TableField("email")
    @ApiModelProperty(value = "邮箱")
    private String email;
            /**
         * 甜甜圈，类比B站硬币
         */
    @TableField("donut")
    @ApiModelProperty(value = "甜甜圈，类比B站硬币")
    private Integer donut;
            /**
         * 会员积分
         */
    @TableField("member_point")
    @ApiModelProperty(value = "会员积分")
    private Integer memberPoint;
            /**
         * 信息修改日期
         */
    @TableField("update_time")
    @ApiModelProperty(value = "信息修改日期")
    private String updateTime;
            /**
         * 认证信息/交由宣言
         */
    @TableField("description")
    @ApiModelProperty(value = "认证信息/交由宣言")
    private String description;
            /**
         * 所在地
         */
    @TableField("place")
    @ApiModelProperty(value = "所在地")
    private String place;
            /**
         * 是否开通了会员
         */
    @TableField("is_vip")
    @ApiModelProperty(value = "是否开通了会员")
    private Integer isVip;
            /**
         * 个性签名
         */
    @TableField("sign")
    @ApiModelProperty(value = "个性签名")
    private String sign;
            /**
         * 爱好
         */
    @TableField("interest")
    @ApiModelProperty(value = "爱好")
    private String interest;
            /**
         * 等级
         */
    @TableField("grade_level")
    @ApiModelProperty(value = "等级")
    private Integer gradeLevel;

            /**
         * 是否实名认证
         */
    @TableField("is_certificated")
    @ApiModelProperty(value = "是否实名认证")
    private Integer isCertificated;
            /**
         * -2：封号中  -1：已注销  0：正常
         */
    @TableField("member_status")
    @ApiModelProperty(value = "-2：封号中  -1：已注销  0：正常")
    private Integer memberStatus;
    
}
