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
@TableName("cms_comment")
@ApiModel(value="CmsComment对象", description="评论功能")
public class CmsCommentEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "目标视频/文章的ID ")
    @TableField("target_id")
    private Long targetId;

    @ApiModelProperty(value = "评论用户的ID ")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "评论最多1K字")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "评论时间")
    @TableField("comment_date")
    private LocalDateTime commentDate;

    @TableField("like")
    private Integer like;

    @ApiModelProperty(value = "1为折叠状态，2为审核状态")
    @TableField("status")
    private Integer status;

}