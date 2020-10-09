package com.aperture.community.core.controller;

import com.aperture.community.core.module.CmsCategoryEntity;
import com.aperture.community.core.service.CmsCategoryService;
import com.aperture.community.entity.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 目录
 *
 * @author HALOXIAO
 * @email haloxql@gmail.com
 * @date 2020-10-08 22:18:56
 */
@RestController
@RequestMapping("core-service/cmscategory")
public class CmsCategoryController {

    @Autowired
    private CmsCategoryService cmsCategoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResultBean list() {
        return null;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResultBean info(@PathVariable("id") Integer id) {
        return null;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResultBean save(@RequestBody CmsCategoryEntity cmsCategory) {

        return null;
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResultBean update(@RequestBody CmsCategoryEntity cmsCategory) {

        return null;
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResultBean delete(@RequestBody Integer[] ids) {

        return null;
    }

}
