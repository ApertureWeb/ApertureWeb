package com.aperture.community.acl.mapper;

import com.aperture.community.acl.entity.ScsPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JavaJayV
 * @since 2020-09-25
 */
public interface ScsPermissionMapper extends BaseMapper<ScsPermission> {

    List<String> selectAllPermissionValue();

    List<String> selectPermissionValueByUserId(Long userId);

    List<ScsPermission> selectPermissionByUserId(Long userId);
}
