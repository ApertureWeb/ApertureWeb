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
 * @date 2020-10-06 18:33:11
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
	 * 用户id
	 */
	private Integer memberId;
	/**
	 * 圈子id
	 */
	private Integer circleId;
	/**
	 * 在对应圈子里的位置
	 */
	private String position;
	/**
	 * 创建圈子审核状态(1：通过  0：不通过)
	 */
	private Integer status;

}
