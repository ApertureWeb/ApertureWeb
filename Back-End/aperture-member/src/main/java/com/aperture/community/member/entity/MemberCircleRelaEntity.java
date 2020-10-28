package com.aperture.community.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@TableName("ums_member_circle_rela")
public class MemberCircleRelaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 会员id
	 */
	private Long memebrId;
	/**
	 * 圈子id
	 */
	private Long circleId;

	private String circleName;
	/**
	 * 职位:0圈友、1管理员、2圈主
	 */
	private Integer position;

	/**
	 * 用户在圈子的等级
	 */
	private Integer memberGrade;

}
