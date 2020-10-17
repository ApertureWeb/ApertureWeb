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
@TableName("ums_follow")
public class FollowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 关注表id
	 */
	@TableId
	private Long id;
	/**
	 * 被关注的用户id
	 */
	private Long followedId;

	/**
	 * 所属分组id
	 */
	private Long groupId;

	/**
	 * 当前用户id
	 */
	private Long memberId;

}
