package com.aperture.community.core.controller;

import java.util.Arrays;
import java.util.Map;

import com.aperture.community.core.module.CmsCategoryEntity;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.entity.ResultBean;
import com.aperture.community.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aperture.community.core.service.CategoryService;


/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:46:44
 */
@RestController
@RequestMapping("core/category" )
public class CmsCategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    public ResultBean list(@RequestParam Map<String, Object> params) {
        PageUtils page = categoryService.queryPage(params);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}" )
    public ResultBean info(@PathVariable("id" ) Integer id) {
            CmsCategoryEntity category = categoryService.getById(id);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    public ResultBean<Boolean> save(@RequestBody CmsCategoryEntity category) {
            categoryService.save(category);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    public ResultBean<Boolean> update(@RequestBody CmsCategoryEntity category) {
            categoryService.updateById(category);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    public ResultBean<Boolean> delete(@RequestBody Integer[] ids) {
            categoryService.removeByIds(Arrays.asList(ids));

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

}
