package com.aperture.community.core.controller;

import com.aperture.community.core.module.param.CmsCircleParam;
import com.aperture.community.core.module.validation.ValidationGroup;
import com.aperture.community.core.module.vo.CmsCircleVO;
import com.aperture.community.core.service.CmsCircleService;
import com.aperture.community.entity.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/list")
    public ResultBean<CmsCircleVO> list(@RequestParam Long categoryId) {

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
    @PostMapping("/xxxx")
    public ResultBean save(@RequestBody @Validated(ValidationGroup.addGroup.class) CmsCircleParam cmsCircleParam) {
        cmsCircleService.addCricle(cmsCircleParam);
        return null;
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResultBean update(@RequestBody CmsCircleParam cmsCircleParam) {

        return null;
    }


}
