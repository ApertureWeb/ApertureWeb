package com.aperture.community.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-11 21:14:46
 */
@Data
@TableName("ums_login_log")
public class LoginLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 登录日志id
	 */
	@TableId
	private Long id;
	/**
	 * 用户id
	 */
	private Long memberId;
	/**
	 * 在线时长(分钟)
	 */
	private Integer onlineTime;
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
	 * 注册时间
	 */
	private Date registTime;

	// 0：已下线  1：上线中
	private Integer loginStatus;

	// 封禁时长
	private Integer banTime;

}