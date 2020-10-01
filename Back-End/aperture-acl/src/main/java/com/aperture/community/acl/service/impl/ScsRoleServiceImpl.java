package com.aperture.community.acl.service.impl;

import com.aperture.community.acl.entity.ScsRole;
import com.aperture.community.acl.entity.ScsUserRoleRelation;
import com.aperture.community.acl.mapper.ScsRoleMapper;
import com.aperture.community.acl.service.IScsRoleService;
import com.aperture.community.acl.service.IScsUserRoleRelationService;
import com.aperture.community.acl.service.IScsUserService;
import com.aperture.entity.IdWorker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.beans.beancontext.BeanContextServiceAvailableEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JavaJayV
 * @since 2020-09-25
 */
@Service
public class ScsRoleServiceImpl extends ServiceImpl<ScsRoleMapper, ScsRole> implements IScsRoleService {

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private IScsUserRoleRelationService userRoleService;

    @Override
    public void saveRole(ScsRole role) {
        Long roleId = restTemplate.getForObject(IdWorker.url, long.class);
        role.setId(roleId);
        baseMapper.insert(role);
    }

    /**
     * 根据查询条件（角色名）获取角色分页列表
     * @param page
     * @param limit
     * @param role
     * @return
     */
    @Override
    public Page<ScsRole> index(Long page, Long limit, ScsRole role) {
        Page<ScsRole> pageParam = new Page<>(page, limit);
        QueryWrapper<ScsRole> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(role.getName())) {
            wrapper.like("name", role.getName());
        }
        this.page(pageParam, wrapper);
        return pageParam;
    }

    /**
     * 根据userId获取该user的所有Role数据
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> findRoleByUserId(Long userId) {
        // 获取所有角色列表
        List<ScsRole> allRolesList = baseMapper.selectList(null);
        // 根据userId从userRole关系表中获取到对应的roleIdList结果集
        List<ScsUserRoleRelation> existUserRoleList = userRoleService.list(new QueryWrapper<ScsUserRoleRelation>().eq("user_id", userId).select("role_id"));
        // 获取userRole信息表中的roleId集
        List<Long> existRoleIdList = existUserRoleList.stream().map(e -> e.getRoleId()).collect(Collectors.toList());
        // 存放查询的role数据结果集
        ArrayList<ScsRole> assignRoles = new ArrayList<>();
        for(ScsRole role : allRolesList) {
            if(existRoleIdList.contains(role.getId())) {
                // 获取该user的所有role信息
                assignRoles.add(role);
            }
        }
        HashMap<String, Object> roleMap = new HashMap<>();
        roleMap.put("assignRoles", assignRoles);
        roleMap.put("allRolesList", allRolesList);
        return roleMap;
    }

    /**
     * 根据userId分配角色
     * @param userId
     * @param roleIds
     */
    @Override
    public void saveUserRoleRelationShip(Long userId, Long[] roleIds) {
        // 先remove
        userRoleService.remove(new QueryWrapper<ScsUserRoleRelation>().eq("user_id", userId));
        ArrayList<ScsUserRoleRelation> userRoleList = new ArrayList<>();
        for(Long roleId : roleIds) {
            if(StringUtils.isEmpty(roleId)) continue;
            ScsUserRoleRelation userRole = new ScsUserRoleRelation();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            userRoleList.add(userRole);
        }
        userRoleService.saveBatch(userRoleList);
    }

    /**
     * 根据userId查询user的roleList
     * @param userId
     * @return
     */
    @Override
    public List<ScsRole> selectRoleByUserId(Long userId) {
        // 根据userId查询出userRoleList，但只包含了roleId
        List<ScsUserRoleRelation> userRoleList = userRoleService.list(new QueryWrapper<ScsUserRoleRelation>().eq("user_id", userId).select("roleId"));
        List<Long> roleIdList = userRoleList.stream().map(item -> item.getRoleId()).collect(Collectors.toList());
        List<ScsRole> roleList = new ArrayList<>();
        if(roleIdList.size() > 0) {
            roleList = baseMapper.selectBatchIds(roleIdList);
        }
        return roleList;
    }


}
