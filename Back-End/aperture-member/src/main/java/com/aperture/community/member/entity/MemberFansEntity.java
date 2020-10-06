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
@TableName("ums_member_fans")
public class MemberFansEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 该用户的粉丝id
	 */
	@TableId
	private Integer fansId;
	/**
	 * 用户id
	 */
	private Integer memberId;

}
