package com.aperture.community.core.module;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Time;

/**
 * @author HALOXIAO
 * @email haloxql@gmail.com
 * @date 2020-10-10 10:39:15
 */
@Data
@TableName("cms_circle")
@ApiModel(value = "CmsCircle对象}", description = "comments")
public class CmsCircleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id", type = IdType.NONE)
    @ApiModelProperty(value = "")
    private Long id;
    /**
     * 主题，二级分类id
     */

    @TableField("category_id")

    @ApiModelProperty(value = "主题，二级分类id")
    private Long categoryId;
    /**
     * 圈名
     */

    @TableField("name")

    @ApiModelProperty(value = "圈名")
    private String name;
    /**
     * （存疑）公开状态，0：未公开   1：已公开
     */

    @TableField("is_public")

    @ApiModelProperty(value = "（存疑）公开状态，0：未公开   1：已公开")
    private Integer isPublic;
    /**
     * 置顶状态，0：置顶   1：未置顶
     */

    @TableField("is_top")

    @ApiModelProperty(value = "置顶状态，0：置顶   1：未置顶")
    private Integer isTop;
    /**
     * 审核状态，0：未审核   1：审核通过
     */

    @TableField("status")

    @ApiModelProperty(value = "审核状态，0：未审核   1：审核通过")
    private Integer status;
    /**
     * 热度
     */

    @TableField("hot")
    @ApiModelProperty(value = "热度")
    private Integer hot;
    /**
     * 会员数
     */

    @TableField("menber_num")

    @ApiModelProperty(value = "会员数")
    private Integer menberNum;
    /**
     * 帖子数
     */

    @TableField("article_num")

    @ApiModelProperty(value = "帖子数")
    private Integer articleNum;
    /**
     * 视频数
     */

    @TableField("video_num")

    @ApiModelProperty(value = "视频数")
    private Integer videoNum;
    /**
     *
     */

    @TableField("url")

    @ApiModelProperty(value = "")
    private String url;
    /**
     * 封面url
     */

    @TableField("image")
    @ApiModelProperty(value = "封面url")
    private String image;
    /**
     * 图标url
     */

    @TableField("icon")

    @ApiModelProperty(value = "图标url")
    private String icon;
    /**
     * 创建时间
     */

    @TableField("gmt_create")

    @ApiModelProperty(value = "创建时间 ")
    private Time gmtCreate;
}
