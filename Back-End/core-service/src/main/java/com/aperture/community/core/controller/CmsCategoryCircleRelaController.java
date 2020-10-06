package com.aperture.community.core.controller;

import java.util.Arrays;
import java.util.Map;

import com.aperture.community.core.module.CmsCategoryCircleRelaEntity;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.entity.ResultBean;
import com.aperture.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aperture.community.core.service.CmsCategoryCircleRelaService;


/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:46:44
 */
@RestController
@RequestMapping("core/categorycirclerela" )
public class CmsCategoryCircleRelaController {
    @Autowired
    private CmsCategoryCircleRelaService cmsCategoryCircleRelaService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    public ResultBean list(@RequestParam Map<String, Object> params) {
        PageUtils page = cmsCategoryCircleRelaService.queryPage(params);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return null;
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public ResultBean info(@PathVariable("id") Long id){
        CmsCategoryCircleRelaEntity categoryBrandRelation = cmsCategoryCircleRelaService.getById(id);

        return null;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResultBean save(@RequestBody CmsCategoryCircleRelaEntity categoryBrandRelation){
        cmsCategoryCircleRelaService.save(categoryBrandRelation);
        return null;
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResultBean update(@RequestBody CmsCategoryCircleRelaEntity categoryBrandRelation){
        cmsCategoryCircleRelaService.updateById(categoryBrandRelation);
        return null;
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResultBean delete(@RequestBody Long[] ids){
        cmsCategoryCircleRelaService.removeByIds(Arrays.asList(ids));
        return null;
    }

}
