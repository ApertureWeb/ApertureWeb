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
@TableName("ums_subscribe")
public class SubscribeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订阅主键
	 */
	@TableId
	private Long id;
	/**
	 * 用户id
	 */
	private Long memberId;
	/**
	 * 目标id
	 */
	private Long targetId;

}
