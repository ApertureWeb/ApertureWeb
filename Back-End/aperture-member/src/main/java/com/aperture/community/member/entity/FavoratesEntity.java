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
@TableName("ums_favorates")
public class FavoratesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 收藏夹分类id
	 */
	@TableId
	private Long id;
	/**
	 * 收藏夹名称
	 */
	private String name;
	/**
	 * 收藏夹里的作品数量
	 */
	private Integer collectionCount;
	/**
	 * 用户id
	 */
	private Long memberId;
	/**
	 * 收藏夹描述
	 */
	private String description;

}
