package com.aperture.community.core.module;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 评论的回复
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-10-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_reply")
@ApiModel(value = "UmsReply对象", description = "评论的回复")
public class UmsReplyEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "目标评论ID")
    @TableField("target_id")
    private Long targetId;

    @ApiModelProperty(value = "默认为0。判断是否是楼中楼，如果是，则为目标回复的id；如果不是，则为0")
    @TableField("root_id")
    private Long rootId;

    @ApiModelProperty(value = "评论ID")
    @TableField("comment_id")
    private Long commentId;

    @ApiModelProperty(value = "内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "评论日期")
    @TableField("comment_date")
    private LocalDateTime commentDate;

    @TableField("like")
    private Integer like;

    @ApiModelProperty(value = "0,为正常状态，1为折叠状态，2为审核状态")
    @TableField("status")
    private Integer status;


}
