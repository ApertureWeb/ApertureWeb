package com.aperture.community.core.controller;

import com.aperture.community.core.module.CmsCircleEntity;
import com.aperture.community.core.module.param.CmsCircleParam;
import com.aperture.community.core.service.CmsCircleService;
import com.aperture.community.entity.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author HALOXIAO
 * @email haloxql@gmail.com
 * @date 2020-10-10 10:39:15
 */
@RestController
@RequestMapping("/cmscircle")
public class CmsCircleController {
    @Autowired
    private CmsCircleService cmsCircleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResultBean list(@RequestParam Map<String, Object> params) {
        return null;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResultBean info(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResultBean save(@RequestBody CmsCircleParam cmsCircleParam) {

        return null;
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResultBean update(@RequestBody CmsCircleEntity cmsCircle) {

        return null;
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResultBean delete(@RequestBody Long[] ids) {

        return null;
    }

}
