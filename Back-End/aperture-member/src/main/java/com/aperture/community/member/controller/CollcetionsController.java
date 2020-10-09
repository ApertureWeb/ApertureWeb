package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aperture.community.member.entity.CollcetionsEntity;
import com.aperture.community.member.service.CollcetionsService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;


/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-09 13:01:14
 */
@RestController
@RequestMapping("member/collcetions")
public class CollcetionsController {
    @Autowired
    private CollcetionsService collcetionsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = collcetionsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        CollcetionsEntity collcetions = collcetionsService.getById(id);

        return R.ok().put("collcetions", collcetions);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CollcetionsEntity collcetions) {
        collcetionsService.save(collcetions);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CollcetionsEntity collcetions) {
        collcetionsService.updateById(collcetions);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        collcetionsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
