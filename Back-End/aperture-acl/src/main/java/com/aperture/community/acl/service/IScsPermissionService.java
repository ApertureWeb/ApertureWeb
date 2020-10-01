package com.aperture.community.acl.service;

import com.alibaba.fastjson.JSONObject;
import com.aperture.community.acl.entity.ScsPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.security.Permission;
import java.util.List;

public interface  IScsPermissionService extends IService<ScsPermission> {

        List<ScsPermission> queryAllMenu();

        void removeChildById(Long id);

        void saveRolePermission(Long roleId, Long[] permissionIds);

        List<ScsPermission> selectAllMenu(Long roleId);

    void savePermission(ScsPermission permission);

    List<String> selectPermissionValueByUserId(Long userId);

    List<JSONObject> selectPermissionByUserId(Long userId);
}