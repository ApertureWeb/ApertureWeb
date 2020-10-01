package com.aperture.community.acl.service;

import com.aperture.community.acl.entity.ScsRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface  IScsRoleService extends IService<ScsRole> {

    void saveRole(ScsRole role);

    Page<ScsRole> index(Long page, Long limit, ScsRole role);


    Map<String,Object> findRoleByUserId(Long userId);

    void saveUserRoleRelationShip(Long userId, Long[] roleIds);

    List<ScsRole> selectRoleByUserId(Long userId);
}