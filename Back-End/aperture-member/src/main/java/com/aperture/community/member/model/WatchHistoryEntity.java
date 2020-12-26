package com.aperture.community.member.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

    import java.util.Date;

/**
 *
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
@Data
@TableName("ums_watch_history")
@ApiModel(value = "WatchHistory对象}", description = "comments")
public class WatchHistoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 观看历史id
         */
    @TableId(value = "id" , type = IdType.NONE)
    @ApiModelProperty(value = "观看历史id")
    private Long id;
            /**
         * 观看目标的id
         */
    @TableField("target_id")
    @ApiModelProperty(value = "观看目标的id")
    private Long targetId;
            /**
         * 观看目标的名称
         */
    @TableField("target_name")
    @ApiModelProperty(value = "观看目标的名称")
    private String targetName;
            /**
         * 目标的类型  0视频  1帖子  2文章
         */
    @TableField("target_type")
    @ApiModelProperty(value = "目标的类型  0视频  1帖子  2文章")
    private Integer targetType;
            /**
         * 看到哪一集
         */
    @TableField("watch_episode")
    @ApiModelProperty(value = "看到哪一集")
    private Integer watchEpisode;
            /**
         * 看了几分钟
         */
    @TableField("watch_minutes")
    @ApiModelProperty(value = "看了几分钟")
    private Integer watchMinutes;
            /**
         * 用户id
         */
    @TableField("member_id")
    @ApiModelProperty(value = "用户id")
    private Long memberId;
    
}
