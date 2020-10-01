package com.aperture.community.acl.mapper;

import com.aperture.community.acl.entity.ScsUserRoleRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * user和role一一对应，为了确保安全模块与用户模块之间不会产生过多耦合，单独分出一张表 Mapper 接口
 * </p>
 *
 * @author JavaJayV
 * @since 2020-09-25
 */
public interface ScsUserRoleRelationMapper extends BaseMapper<ScsUserRoleRelation> {

}
