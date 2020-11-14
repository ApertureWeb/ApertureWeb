package com.aperture.community.core.module;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Null;

/**
 * <p>
 *
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cms_article")
@ApiModel(value = "CmsArticle对象", description = "")
public class CmsArticleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    @TableField("title")
    private String title;

    @TableField("content")
    private String content;

    @TableField("description")
    private String description;

    @TableField("icon")
    private String icon;

    @TableField("user_uid")
    private Long userUid;

    @TableField("circle_id")
    private Long circleId;

    @TableField("status")
    private Integer status;

}
