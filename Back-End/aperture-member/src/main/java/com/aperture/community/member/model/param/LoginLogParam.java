package com.aperture.community.member.model.param;

import com.aperture.community.member.model.validation.AddGroup;
import com.aperture.community.member.model.validation.ValidationGroup;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class LoginLogParam{

    /**
     * 登录日志id
     */
    @Null(groups = ValidationGroup.addGroup.class)
    private Long id;

    /**
     * 用户登录时的ip
     */
    private String ip;
    /**
     * 用户登录时所在的城市
     */
    private String city;
    /**
     * 登录类型[1-web，2-app]
     */
    private Integer loginType;
    /**
     * 在线时长(分钟)
     */
    private Integer onlineTime;
    /**
     * 注册时间
     */
    private String registTime;
    /**
     * 0：已下线  1：上线中
     */
    private Integer loginStatus;
    /**
     * 封号时长，单位：分钟
     */
    private Integer banTime;

}