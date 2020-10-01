package com.aperture.community.acl.service.impl;

import com.aperture.community.acl.entity.ScsUserRoleRelation;
import com.aperture.community.acl.mapper.ScsUserRoleRelationMapper;
import com.aperture.community.acl.service.IScsUserRoleRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * user和role一一对应，为了确保安全模块与用户模块之间不会产生过多耦合，单独分出一张表 服务实现类
 * </p>
 *
 * @author JavaJayV
 * @since 2020-09-25
 */
@Service
public class ScsUserRoleRelationServiceImpl extends ServiceImpl<ScsUserRoleRelationMapper, ScsUserRoleRelation> implements IScsUserRoleRelationService {

}
