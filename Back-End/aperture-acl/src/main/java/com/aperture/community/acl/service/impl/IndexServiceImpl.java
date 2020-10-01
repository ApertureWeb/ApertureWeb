package com.aperture.community.acl.service.impl;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr;
import com.alibaba.fastjson.JSONObject;
import com.aperture.community.acl.entity.ScsRole;
import com.aperture.community.acl.entity.ScsUser;
import com.aperture.community.acl.service.IScsPermissionService;
import com.aperture.community.acl.service.IScsRoleService;
import com.aperture.community.acl.service.IScsUserService;
import com.aperture.community.acl.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: JayV
 * @Date: 2020-9-26 14:16
 * @Description:
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IScsUserService userService;

    @Autowired
    private IScsRoleService roleService;

    @Autowired
    private IScsPermissionService permissionService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据username获取该用户的用户登录信息，包括认证和权限信息
     */
    @Override
    public Map<String, Object> getUserInfo(String username) {
        // 存放用户信息的结果集
        HashMap<String, Object> result = new HashMap<>();
        // 根据username获取到user对象
        ScsUser user = userService.selectByUsername(username);
        //  根据userId获取到roleList集合
        List<ScsRole> roleList =  roleService.selectRoleByUserId(user.getId());
        // 从roleList中获取到roleNameList结果集
        List<String> roleNameList = roleList.stream().map(role -> role.getName()).collect(Collectors.toList());
        if(roleNameList.size() == 0) {
            roleNameList.add(""); // 防止向前端返回null，从而引起报错，如果没有角色则返回空角色
        }
        // 根据userId获取到用户操作权限值
        List<String> permissionValueList = permissionService.selectPermissionValueByUserId(user.getId());
        // username为key，权限值作为value放到redis中
        redisTemplate.opsForValue().set(username, permissionValueList);
        result.put("username", user.getUsername());
        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("roles", roleNameList);
        result.put("permissionValueList", permissionValueList);
        return result;
    }

    /**
     * 根据username获取json格式的菜单列表
     * @param username
     * @return
     */
    @Override
    public List<JSONObject> getMenu(String username) {
        ScsUser user = userService.selectByUsername(username);
        List<JSONObject> permissionList = permissionService.selectPermissionByUserId(user.getId());
        return permissionList;
    }
}