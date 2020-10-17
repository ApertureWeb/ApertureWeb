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
@TableName("ums_follow_group")
public class FollowGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 关注分组id
	 */
	@TableId
	private Long id;
	/**
	 * 分组名
	 */
	private String name;
	/**
	 * 分组的关注数
	 */
	private Integer followCount;
	/**
	 * 是否为默认分组
	 */
	private Integer isDefault;
	/**
	 * 该分组所属用户id
	 */
	private Long memberId;

}
