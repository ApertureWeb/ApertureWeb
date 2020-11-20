package com.aperture.community.member.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

    import java.util.Date;

/**
 *
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
@Data
@TableName("ums_collection")
@ApiModel(value = "Collection对象}", description = "comments")
public class CollectionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 收藏主键id
     */
    @TableId(value = "id" , type = IdType.NONE)
    @ApiModelProperty(value = "收藏主键id")
    private Long id;

    /**
     * 收藏名
     */
    @TableField("name")
    @ApiModelProperty(value = "收藏名")
    private String name;

    /**
     * 收藏日期
     */
    @TableField("collection_date")
    @ApiModelProperty(value = "收藏日期")
    private String collectionDate;

    /**
     * 收藏目标的id
     */
    @TableField("target_id")
    @ApiModelProperty(value = "收藏目标的id")
    private Long targetId;

    /**
     * 所属收藏夹id
     */
    @TableField("favorates_id")
    @ApiModelProperty(value = "所属收藏夹id")
    private Long favoratesId;
    /**
     * 当前用户id
     */
    @TableField("member_id")
    @ApiModelProperty(value = "当前用户id")
    private Long memberId;
    
}
