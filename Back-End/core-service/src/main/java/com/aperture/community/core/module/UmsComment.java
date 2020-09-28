package com.aperture.community.core.module;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 评论功能
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_comment")
@ApiModel(value="UmsComment对象", description="评论功能")
public class UmsComment implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "目标视频/文章的ID ")
    @TableField("target_id")
    private Long targetId;

    @ApiModelProperty(value = "如果为主评论和回复，则为0。用于标识楼中楼（回复对话）。" +
            "值得注意的是，这个root_id的传播属于感染式的，当第一个人为楼中楼的时候，日后所有回复它的人都将被感染，以此类推")
    @TableField("root_id")
    private Long rootId;

    @ApiModelProperty(value = "主评论默认为0，如果为回复，则为评论的ID")
    @TableField("reply_id")
    private Long replyId;

    @ApiModelProperty(value = "评论用户的ID ")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "评论最多1K字")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "评论时间")
    @TableField("comment_date")
    private LocalDateTime commentDate;

    @ApiModelProperty(value = "1为折叠状态，2为审核状态")
    @TableField("status")
    private Integer status;


}
