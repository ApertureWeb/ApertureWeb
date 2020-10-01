package com.aperture.community.acl.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * user和role一一对应，为了确保安全模块与用户模块之间不会产生过多耦合，单独分出一张表
 * </p>
 *
 * @author JavaJayV
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ScsUserRoleRelation对象", description="user和role一一对应，为了确保安全模块与用户模块之间不会产生过多耦合，单独分出一张表")
public class ScsUserRoleRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    private Long userId;

    private Long roleId;

    @ApiModelProperty(value = "状态( -2:禁用  -1:删除   1:正常)")
    private Integer status;


}
