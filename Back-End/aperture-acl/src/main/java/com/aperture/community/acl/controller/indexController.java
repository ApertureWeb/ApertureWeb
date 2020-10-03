package com.aperture.community.acl.controller;

import com.alibaba.fastjson.JSONObject;
import com.aperture.community.acl.service.IndexService;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.entity.ResultBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Auther: JayV
 * @Date: 2020-9-26 14:16
 * @Description:
 */

@RestController
@RequestMapping("/admin/acl/index")
public class indexController {

    @Autowired
    private IndexService indexService;

    @ApiOperation("根据token获取用户的用户登录信息")
    @GetMapping("info")
    public ResultBean info() {
        // 从认证容器中获取当期用户的用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return new ResultBean<>("根据token获取用户的用户登录信成功", RESULT_BEAN_STATUS_CODE.SUCCESS, userInfo);
    }

    @ApiOperation(value = "获取用户的权限菜单列表")
    @GetMapping("getMenu")
    public ResultBean getMenu() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // 根据username获取到该用户的JSON格式的菜单列表
        List<JSONObject> permissionList = indexService.getMenu(username);
        return new ResultBean<>("获取用户的权限菜单列表成功", RESULT_BEAN_STATUS_CODE.SUCCESS, permissionList);
    }


}