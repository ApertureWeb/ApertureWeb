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
@TableName("ums_member_grade")
public class MemberGradeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 等级id
	 */
	@TableId
	private Integer id;
	/**
	 * 当前等级
	 */
	private Integer currentGrade;
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
	 * 是否开通了大会员
	 */
	private Integer isVip;
	/**
	 * 最高等级
	 */
	private Integer topGrade;

}
