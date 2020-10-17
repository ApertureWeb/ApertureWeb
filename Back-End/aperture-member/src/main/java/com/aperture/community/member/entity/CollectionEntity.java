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
@TableName("ums_collection")
public class CollectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 收藏主键id
	 */
	@TableId
	private Long id;
	/**
	 * 收藏名
	 */
	private String name;

	/**
	 * 收藏日期
	 */
	private Date collectionDate;
	/**
	 * 收藏目标的id
	 */
	private Long targetId;
	/**
	 * 所属收藏夹id
	 */
	private Long favoratesId;
	/**
	 * 当前用户id
	 */
	private Long memebrId;

}
