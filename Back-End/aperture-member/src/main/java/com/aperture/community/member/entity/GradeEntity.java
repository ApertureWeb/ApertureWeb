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
@TableName("ums_grade")
public class GradeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 等级id
	 */
	@TableId
	private Long id;
	/**
	 * 当前等级
	 */
	private Integer gradeLevel;
	/**
	 * 等级名称
	 */
	private String name;
	/**
	 * 是否为默认等级
	 */
	private Integer isDefaultGrade;
	/**
	 * 当前等级升级需要的经验
	 */
	private Integer growthValue;
	/**
	 * 签到增加的经验
	 */
	private Integer signInGrowthValue;
	/**
	 * 发布作品增加的经验
	 */
	private Integer publishGrowthValue;
	/**
	 * 评论增加的经验
	 */
	private Integer commentGrowthValue;
	/**
	 * 是否有会员价格优惠特权
	 */
	private Integer isVipDiscount;
	/**
	 * 等级描述
	 */
	private String description;
	/**
	 * 是否可以开通创建圈子
	 */
	private Integer isCreateCircle;
	/**
	 * 最高等级
	 */
	private Integer topGrade;

}
