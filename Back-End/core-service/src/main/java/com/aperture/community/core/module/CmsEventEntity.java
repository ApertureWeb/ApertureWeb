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
 * @author HALOXIAO
 * @email haloxql@gmail.com
 * @date 2020-10-05 20:51:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cms_event")
@ApiModel(value = "CmsEvent映射对象", description = "")
public class CmsEventEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 内容Id
     */
    @ApiModelProperty(value = " 内容Id")
    @TableId(value = "content_id", type = IdType.NONE)

    private Long contentId;
    /**
     * 点赞数
     */

    @ApiModelProperty(value = "点赞数")
    @TableField(value = "like")

    private Integer like;
    /**
     * 收藏数
     */

    @ApiModelProperty(value = "收藏数")
    @TableField(value = "store")

    private Integer store;
    /**
     * 投喂数
     */

    @ApiModelProperty(value = "投喂数")
    @TableField(value = "donut")
    private Integer donut;
    /**
     * event类型：0为视频，1为文章
     */
    @ApiModelProperty(value = "event类型：0为视频，1为文章")
    @TableField(value = "type")
    private Integer type;

}
