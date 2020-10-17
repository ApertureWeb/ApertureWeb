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
@TableName("ums_watch_history")
public class WatchHistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 观看历史id
	 */
	@TableId
	private Long id;
	/**
	 * 观看目标的id
	 */
	private Long targetId;
	/**
	 * 观看目标的名称
	 */
	private String targetName;
	/**
	 * 目标的类型,0视频  1帖子  2文章
	 */
	private Integer targetType;
	/**
	 * 看了多少分钟
	 */
	private Integer watchMinutes;
	/**
	 * 看到第几集
	 */
	private Integer watchEpisode;
	/**
	 * 用户id
	 */
	private Long memberId;

}
