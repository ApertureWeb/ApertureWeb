package com.aperture.community.core.module;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 目录
 *
 * @author HALOXIAO
 * @email haloxql@gmail.com
 * @date 2020-10-08 22:18:56
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cms_category")
@ApiModel(value = "CmsCategory对象}", description = "comments")
public class CmsCategoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    @TableId(value = "id", type = IdType.NONE)


    @ApiModelProperty(value = "分类id")
    private Integer id;
    /**
     * 分类名称
     */

    @TableField("name")

    @ApiModelProperty(value = "分类名称")
    private String name;
    /**
     * 父分类id,顶层默认为0
     */

    @TableField("parent_cid")

    @ApiModelProperty(value = "父分类id,顶层默认为0")
    private Integer parentCid;
    /**
     * 分类层级
     */

    @TableField("level")

    @ApiModelProperty(value = "分类层级")
    private String level;
    /**
     * 是否显示[0-不显示，1显示]
     */

    @TableField("show_status")

    @ApiModelProperty(value = "是否显示[0-不显示，1显示]")
    private Integer showStatus;
    /**
     * 排序
     */

    @TableField("sort")

    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 图标地址
     */

    @TableField("icon")

    @ApiModelProperty(value = "图标地址")
    private String icon;
    /**
     * 圈子数量
     */

    @TableField("circle_count")

    @ApiModelProperty(value = "圈子数量")
    private String circleCount;
    /**
     * 圈子状态[0为正常，1为审核中]
     */

    @TableField("status")

    @ApiModelProperty(value = "圈子状态[0为正常，1为审核中]")
    private Integer status;

}
