package com.aperture.community.core.module;

import com.aperture.community.core.module.validation.ValidationGroup;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

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
@TableName("cms_video")
@ApiModel(value = "CmsVideo对象", description = "用于存放视频")
public class CmsVideoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.NONE)
       private Long id;


    @TableField("title")
    private String title;

    @TableField("description")
    private String description;

    @TableField("url")
    private String url;

    @TableField("like")
    private Integer like;

    @TableField("coins")
    private Integer coins;

    @TableField("user_id")
    private Integer userId;

    @TableField("cicle_id")
    private Integer cicleId;

    @TableField("status")
    private Integer status;


}
