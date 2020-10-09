package com.aperture.community.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-09 13:01:14
 */
@Data
@TableName("ums_member_login_log")
public class MemberLoginLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 登录日志id
     */
    @TableId
    private Integer id;
    /**
     * 用户id
     */
    private Integer memberId;
    /**
     * 用户创建时间
     */
    private Date createTime;
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

}
