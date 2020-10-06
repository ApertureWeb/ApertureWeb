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
@TableName("ums_member_watch_history")
public class MemberWatchHistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 观看历史id
	 */
	@TableId
	private Integer id;
	/**
	 * 观看目标的id
	 */
	private Integer targetId;
	/**
	 * 观看目标的名称
	 */
	private String targetName;
	/**
	 * 目标的类型
	 */
	private Integer targetType;
	/**
	 * 观看到哪个时间点
	 */
	private Date watchWhere;

}
