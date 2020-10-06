package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aperture.community.member.entity.FavoratesEntity;
import com.aperture.community.member.service.FavoratesService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;


/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */
@RestController
@RequestMapping("member/favorates" )
public class FavoratesController {
    @Autowired
    private FavoratesService favoratesService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    @RequiresPermissions("member:favorates:list" )
    public ResultBean list(@RequestParam Map<String, Object> params) {
        PageUtils page = favoratesService.queryPage(params);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}" )
    @RequiresPermissions("member:favorates:info" )
    public ResultBean info(@PathVariable("id" ) Integer id) {
            FavoratesEntity favorates = favoratesService.getById(id);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    @RequiresPermissions("member:favorates:save" )
    public ResultBean<Boolean> save(@RequestBody FavoratesEntity favorates) {
            favoratesService.save(favorates);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    @RequiresPermissions("member:favorates:update" )
    public ResultBean<Boolean> update(@RequestBody FavoratesEntity favorates) {
            favoratesService.updateById(favorates);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    @RequiresPermissions("member:favorates:delete" )
    public ResultBean<Boolean> delete(@RequestBody Integer[] ids) {
            favoratesService.removeByIds(Arrays.asList(ids));

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

}
