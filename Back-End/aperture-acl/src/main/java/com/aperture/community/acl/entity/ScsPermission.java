package com.aperture.community.acl.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * <p>
 * 
 * </p>
 *
 * @author JavaJayV
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ScsPermission对象", description="用户权限")
public class ScsPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    @ApiModelProperty(value = "拥有的基本权限（如增删改查）")
    private String detail;

    private String name;

    private String description;

    @ApiModelProperty(value = "类型（菜单：1， 按钮：2）")
    private Integer type;

    @ApiModelProperty(value = "上级菜单id")
    private Long pid;

    @ApiModelProperty(value = "菜单访问路径")
    private String path;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "状态( -2:禁用  -1:删除   1:正常)")
    private Integer status;


    @ApiModelProperty(value = "前端组件路径")
    private String component;

    @ApiModelProperty(value = "权限值")
    @TableField("permissionValue")
    private String permissionValue;


    @ApiModelProperty(value = "层级")
    @TableField(exist = false)
    private Integer level;

    @ApiModelProperty(value = "下级")
    @TableField(exist = false)
    private List<ScsPermission> children;

    @ApiModelProperty(value = "是否选中")
    @TableField(exist = false)
    private boolean isSelect;

    @TableField()
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime gmtModified;
}
