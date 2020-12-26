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
@TableName("ums_favorates")
@ApiModel(value = "Favorates对象}", description = "comments")
public class FavoratesEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 收藏夹id
     */
    @TableId(value = "id" , type = IdType.NONE)
    @ApiModelProperty(value = "收藏夹id")
    private Long id;

    /**
     * 收藏夹名称
     */
    @TableField("name")
    @ApiModelProperty(value = "收藏夹名称")
    private String name;

    /**
     * 用户id
     */
    @TableField("member_id")
    @ApiModelProperty(value = "用户id")
    private Long memberId;

            /**
         * 收藏夹描述
         */
    @TableField("description")
    @ApiModelProperty(value = "收藏夹描述")
    private String description;

            /**
         * 是否公开
         */
    @TableField("is_public")
    @ApiModelProperty(value = "是否公开")
    private Integer isPublic;

    /**
     * 每个收藏夹的收藏数
     */
    @TableField("collection_count")
    @ApiModelProperty(value = "每个收藏夹的收藏数")
    private Integer collectionCount;

}
