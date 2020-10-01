package com.aperture.community.acl.controller;

import com.aperture.community.acl.entity.ScsPermission;
import com.aperture.community.acl.service.IScsPermissionService;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.entity.ResultBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JavaJayV
 * @since 2020-09-25
 */
@RestController
@RequestMapping("/admin/acl/permission")
public class ScsPermissionController {

    @Autowired
    private IScsPermissionService permissionService;

    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public ResultBean savePermission(@RequestBody ScsPermission permission) {
        permissionService.savePermission(permission);
        return new ResultBean("新增菜单成功", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public ResultBean remove(@PathVariable("id") Long id) {
        permissionService.removeChildById(id);
        return new ResultBean("递归删除菜单成功", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public ResultBean updateById(@RequestBody ScsPermission permission) {
        permissionService.updateById(permission);
        return new ResultBean("修改菜单成功", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public ResultBean indexAllPermission() {
        List<ScsPermission> list = permissionService.queryAllMenu();
        return new ResultBean<>("查询所有菜单成功", RESULT_BEAN_STATUS_CODE.SUCCESS, list);
    }

    @ApiOperation(value = "根据角色去获取菜单")
    @GetMapping("/toAssign/{roleId}")
    public ResultBean toAssign(@PathVariable("roleId") Long roleId) {
        List<ScsPermission> permissionList = permissionService.selectAllMenu(roleId);
        return new ResultBean<>("根据角色去获取菜单成功", RESULT_BEAN_STATUS_CODE.SUCCESS, permissionList);
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public ResultBean doAssign(@RequestParam Long roleId, @RequestParam Long[] permissionIds) {
        permissionService.saveRolePermission(roleId, permissionIds);
        return new ResultBean("角色分配权限成功", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }





}

