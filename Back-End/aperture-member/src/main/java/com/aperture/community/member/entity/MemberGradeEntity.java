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
 * @date 2020-10-12 21:32:49
 */
@Data
@TableName("ums_member_grade")
public class MemberGradeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户等级id
	 */
	@TableId
	private Integer id;
	/**
	 * 当前等级
	 */
	private String currentGrade;
	/**
	 * 当前等级经验值
	 */
	private String currentValue;

}
