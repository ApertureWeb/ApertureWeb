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
 * @date 2020-10-11 13:30:06
 */
@Data
@TableName("ums_member")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 手机号
	 */
	private Integer telephone;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 性别[0：男  1：女]
	 */
	private Integer gender;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 头像url
	 */
	private String headUrl;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 甜甜圈，类比B站硬币
	 */
	private Integer donut;
	/**
	 * 大会员积分
	 */
	private Integer integration;
	/**
	 * 信息修改日期
	 */
	private Date updateTime;
	/**
	 * 认证用户为认证信息 普通用户为交友宣言
	 */
	private String description;
	/**
	 * 所在地
	 */
	private String place;
	/**
	 * 个性签名
	 */
	private String sign;
	/**
	 * 爱好
	 */
	private String intrest;
	/**
	 * 注册时间
	 */
	private Date startTime;
	/**
	 * 在线时长(分钟)
	 */
	private Integer online;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 等级id
	 */
	private Integer gradeUid;
	/**
	 * 关注数
	 */
	private Integer followCount;
	/**
	 * 粉丝数
	 */
	private Integer fansCount;
	/**
	 * 是否实名认证
	 */
	private String isCertificated;

}
