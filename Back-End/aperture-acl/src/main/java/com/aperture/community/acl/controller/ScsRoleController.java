package com.aperture.community.acl.controller;


import com.alibaba.druid.sql.dialect.odps.ast.OdpsAddStatisticStatement;
import com.aperture.community.acl.entity.ScsRole;
import com.aperture.community.acl.service.IScsRoleService;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.entity.ResultBean;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
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
@RequestMapping("/admin/acl/role")
public class ScsRoleController {

    @Autowired
    private IScsRoleService roleService;

    @ApiOperation(value = "新增角色")
    @PostMapping("save")
    public ResultBean save(@RequestBody ScsRole role) {
        roleService.saveRole(role);
        return new ResultBean("新增角色成功", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    @ApiOperation(value = "获取角色分页列表")
    @GetMapping("{page}/{limit}")
    public ResultBean index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable("page") Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable("limit") Long limit,
            @RequestBody ScsRole role) {

        Page<ScsRole> rolePage = roleService.index(page, limit, role);
        return new ResultBean<>("获取角色分页列表成功", RESULT_BEAN_STATUS_CODE.SUCCESS, rolePage);
    }

    @ApiOperation(value = "获取角色")
    @GetMapping("get/{roleId}")
    public ResultBean<ScsRole> get(@PathVariable("roleId") Long roleId) {
        ScsRole role = roleService.getById(roleId);
        return new ResultBean<>("获取角色成功", RESULT_BEAN_STATUS_CODE.SUCCESS, role);
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("update")
    public ResultBean updateById(@RequestBody ScsRole role) {
        roleService.updateById(role);
        return new ResultBean("修改角色成功", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("remove/{roleId}")
    public ResultBean remove(@PathVariable("roleId") Long roleId) {
        roleService.removeById(roleId);
        return new ResultBean("删除角色成功", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    @ApiOperation(value = "根据idLsit批量删除角色")
    @DeleteMapping("batchRemove")
    public ResultBean batchRemove(@RequestBody List<Long> idList) {
        roleService.removeByIds(idList);
        return new ResultBean("根据idLsit批量删除角色成功", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }
}

