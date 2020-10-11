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
@TableName("ums_member_circle_rela")
public class MemberCircleRelaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 会员id
	 */
	private Integer memebrId;
	/**
	 * 圈子id
	 */
	private Integer circleId;
	/**
	 * 会员昵称
	 */
	private String memberNickname;
	/**
	 * 圈子名
	 */
	private String circleName;
	/**
	 * 在圈子的职位
	 */
	private String position;

}
