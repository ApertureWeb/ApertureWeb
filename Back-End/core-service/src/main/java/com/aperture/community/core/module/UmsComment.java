package com.aperture.community.core.module;

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
 * @since 2020-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_comment")
@ApiModel(value="UmsComment对象", description="评论功能")
public class UmsComment implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "目标视频/文章的ID ")
    @TableField("target_id")
    private Long targetId;

    @ApiModelProperty(value = "如果是主评论和回复，那么默认为0，用于标识楼中楼（回复对话）。值得注意的是，这个root_id的传播属于感染式的，当第一个人为楼中楼的时候，日后所有回复它的人都将被感染，以此类推")
    @TableField("root_id")
    private Long rootId;

    @ApiModelProperty(value = "评论用户的ID ")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "评论最多1K字")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = " 是否为楼中楼节点")
    @TableField("is_root")
    private Integer isRoot;

    @ApiModelProperty(value = "评论时间")
    @TableField("comment_date")
    private LocalDateTime commentDate;

    @TableField("status")
    private Integer status;


}
