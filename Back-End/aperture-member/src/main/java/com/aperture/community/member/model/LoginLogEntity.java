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
@TableName("ums_login_log")
@ApiModel(value = "LoginLog对象}", description = "comments")
public class LoginLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 登录日志id
         */
    @TableId(value = "id" , type = IdType.NONE)
    @ApiModelProperty(value = "登录日志id")
    private Long id;
            /**
         * 用户id
         */
    @TableField("member_id")
    @ApiModelProperty(value = "用户id")
    private Long memberId;
            /**
         * 用户登录时的ip
         */
    @TableField("ip")
    @ApiModelProperty(value = "用户登录时的ip")
    private String ip;
            /**
         * 用户登录时所在的城市
         */
    @TableField("city")
    @ApiModelProperty(value = "用户登录时所在的城市")
    private String city;
            /**
         * 登录类型[1-web，2-app]
         */
    @TableField("login_type")
    @ApiModelProperty(value = "登录类型[1-web，2-app]")
    private Integer loginType;
            /**
         * 在线时长(分钟)
         */
    @TableField("online_time")
    @ApiModelProperty(value = "在线时长(分钟)")
    private Integer onlineTime;
            /**
         * 注册时间
         */
    @TableField("regist_time")
    @ApiModelProperty(value = "注册时间")
    private String registTime;
            /**
         * 0：已下线  1：上线中
         */
    @TableField("login_status")
    @ApiModelProperty(value = "0：已下线  1：上线中")
    private Integer loginStatus;
            /**
         * 封号时长，单位：分钟
         */
    @TableField("ban_time")
    @ApiModelProperty(value = "封号时长，单位：分钟")
    private Integer banTime;
    
}
