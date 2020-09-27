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
@TableName("ums_article")
@ApiModel(value = "UmsArticle对象", description = "")
public class UmsArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    @TableField("title")
    private String title;

    @TableField("content")
    private String content;

    @TableField("description")
    private String description;

    @TableField("like")
    private Integer like;

    @TableField("coins")
    private Integer coins;

    @TableField("user_uid")
    private Long userUid;

    @TableField("circle_id")
    private Long circleId;

}
