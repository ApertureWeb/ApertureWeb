package com.aperture.community.acl.controller;


import com.aperture.community.acl.entity.ScsRole;
import com.aperture.community.acl.entity.ScsUser;
import com.aperture.community.acl.service.IScsRoleService;
import com.aperture.community.acl.service.IScsUserService;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.entity.ResultBean;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.bouncycastle.asn1.cmc.TaggedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JavaJayV
 * @since 2020-09-25
 */
@RestController
@RequestMapping("/admin/acl/user")
public class ScsUserController {

    @Autowired
    private IScsUserService userService;

    @Autowired
    private IScsRoleService roleService;

    @ApiOperation(value = "新增管理用户")
    @PostMapping("save")
    public ResultBean save(@RequestBody ScsUser user) {
        userService.saveUser(user);
        return new ResultBean("新增管理用户成功", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    @ApiOperation(value = "删除管理用户")
    @DeleteMapping("remove/{userId}")
    public ResultBean remove(@PathVariable("userId") Long userId) {
        userService.removeById(userId);
        return new ResultBean("删除管理用户成功", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }


    @ApiOperation(value = "根据idList批量删除管理用户")
    @DeleteMapping("batchRemove")
    public ResultBean batchRemove(@RequestBody List<Long> idList) {
        userService.removeByIds(idList);
        return new ResultBean("根据idList批量删除管理用户成功", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    @ApiOperation(value = "修改管理用户")
    @PutMapping("update")
    public ResultBean updateById(@RequestBody ScsUser user) {
        userService.updateById(user);
        return new ResultBean("修改管理用户成功", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    @ApiOperation(value = "根据userId获取角色数据")
    @GetMapping("/getRoleByUserId/{userId}")
    public ResultBean getRoleByUserId(@PathVariable("userId") Long userId) {
        Map<String, Object> roleMap = roleService.findRoleByUserId(userId);
        return new ResultBean<>("根据userId获取角色数据成功", RESULT_BEAN_STATUS_CODE.SUCCESS, roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public ResultBean doAssign(@RequestParam Long userId, @RequestParam Long[] roleIds) {
        roleService.saveUserRoleRelationShip(userId, roleIds);
        return new ResultBean<>("根据用户分配角色成功", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    @ApiOperation(value = "根据查询条件获取管理员分页列表")
    @GetMapping("{page}/{limit}")
    public ResultBean index (
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable("page") Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable("limit") Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody ScsUser userQueryVo) {
         IPage<ScsUser> pageModel = userService.queryUserPage(page, limit, userQueryVo);
        return new ResultBean("根据查询条件获取管理员分页列表成功", RESULT_BEAN_STATUS_CODE.SUCCESS, pageModel);
    }

}

