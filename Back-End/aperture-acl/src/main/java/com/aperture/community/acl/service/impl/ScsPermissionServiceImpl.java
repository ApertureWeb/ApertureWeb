package com.aperture.community.acl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aperture.community.acl.entity.ScsPermission;
import com.aperture.community.acl.entity.ScsRolePermission;
import com.aperture.community.acl.entity.ScsUser;
import com.aperture.community.acl.helper.MenuHelper;
import com.aperture.community.acl.helper.PermissionHelper;
import com.aperture.community.acl.mapper.ScsPermissionMapper;
import com.aperture.community.acl.service.IScsPermissionService;
import com.aperture.community.acl.service.IScsRolePermissionService;
import com.aperture.community.acl.service.IScsUserService;
import com.aperture.entity.IdWorker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JavaJayV
 * @since 2020-09-25
 */
@Service
public class ScsPermissionServiceImpl extends ServiceImpl<ScsPermissionMapper, ScsPermission> implements IScsPermissionService {

    @Autowired
    private IScsRolePermissionService rolePermissionService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IScsUserService userService;

    /**
     * 新增菜单
     * @param permission
     */
    @Override
    public void savePermission(ScsPermission permission) {
        Long permissionId = restTemplate.getForObject(IdWorker.url, long.class);
        permission.setId(permissionId);
        baseMapper.insert(permission);
    }

    /**
     * 根据用户id获取用户权限值
     * @param userId
     * @return
     */
    @Override
    public List<String> selectPermissionValueByUserId(Long userId) {
        List<String> permissionValueList = null;
        if(this.isSysAdmin(userId)) {
            // 系统管理员，获取所有权限, 需要在mapper.xml定义
            permissionValueList = baseMapper.selectAllPermissionValue();
        }else {
            permissionValueList = baseMapper.selectPermissionValueByUserId(userId);
        }
        return permissionValueList;
    }

    /**
     * 根据用户id查询permission集合，并构建层级菜单
     * @param userId
     * @return
     */
    @Override
    public List<JSONObject> selectPermissionByUserId(Long userId) {
        List<ScsPermission> permissionList = null;
        if(this.isSysAdmin(userId)) {
            permissionList = baseMapper.selectList(null);
        } else {
            // 根据userId获取响应权限
            permissionList = baseMapper.selectPermissionByUserId(userId);
        }
        // 构架菜单完成
        List<ScsPermission> permissionTreeList = PermissionHelper.build(permissionList);
        List<JSONObject> result = MenuHelper.bulid(permissionTreeList);
        return result;
    }

    private boolean isSysAdmin(Long userId) {
        ScsUser user = userService.getById(userId);
        if(user != null && "admin".equals(user.getUsername())) {
            return true;
        }
        return false;
    }

    @Override
    public List<ScsPermission> queryAllMenu() {
        QueryWrapper<ScsPermission> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<ScsPermission> res = baseMapper.selectList(wrapper);
        return res;
    }

    /**
     * 递归删除所有菜单
     * @param id
     */
    @Override
    public void removeChildById(Long id) {
        ArrayList<Long> idList = new ArrayList<>();
        this.selectChildListById(id, idList);
        idList.add(id); // 当期菜单也要删除
        baseMapper.deleteBatchIds(idList); // 根据id删除菜单
    }



    /**
     * 根据id递归查询子菜单
     * @param id
     * @param idList
     */
    private void selectChildListById(Long id, List<Long> idList) {
        // 根据id查出子菜单, 只查询id
        List<ScsPermission> childList = baseMapper.selectList(new QueryWrapper<ScsPermission>().eq("pid", id).select("id"));
        // stream流操作，递归操作会自动结束
        childList.stream().forEach(item -> {
            idList.add(item.getId());
            this.selectChildListById(id, idList); // 递归
        });
    }

    /**
     * 角色分配权限
     * @param roleId
     * @param permissionIds
     */
    @Override
    public void saveRolePermission(Long roleId, Long[] permissionIds) {
        List<ScsRolePermission> rolePermissionList = new ArrayList<>();
        for(Long permissionId : permissionIds) {
            ScsRolePermission rolePermission = new ScsRolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionList.add(rolePermission);
        }
        rolePermissionService.saveBatch(rolePermissionList); // 保存到数据库
    }

    /**
     * 根据角色Id获取菜单
     * @param roleId
     * @return
     */
    @Override
    public List<ScsPermission> selectAllMenu(Long roleId) {
        // 获取到根据id升序的所有权限
        List<ScsPermission> allPermissionList = baseMapper.selectList(new QueryWrapper<ScsPermission>().orderByAsc("CAST(id AS SIGNED)"));
        // 根据roleId获取到该角色所有权限
        List<ScsRolePermission> rolePermissionList = rolePermissionService.list(new QueryWrapper<ScsRolePermission>().eq("role_id", roleId));
        for(int i = 0; i < allPermissionList.size(); i++) {
            ScsPermission permission = allPermissionList.get(i);
            for(int j = 0; j < rolePermissionList.size(); j++) {
                ScsRolePermission rolePermission = rolePermissionList.get(j);
                if(rolePermission.getPermissionId().equals(permission.getId())) {
                    // 表明该权限是该角色拥有的
                    permission.setSelect(true);
                }
            }
        }
        // 查询该角色的所有权限菜单后构建树形结构，前端就可以显示层级结构的菜单
        List<ScsPermission> permissionList = PermissionHelper.build(allPermissionList);
        return permissionList;
    }



}
