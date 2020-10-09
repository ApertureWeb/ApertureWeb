package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aperture.community.member.entity.CollectionsFavoratesRelaEntity;
import com.aperture.community.member.service.CollectionsFavoratesRelaService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;


/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-09 13:01:14
 */
@RestController
@RequestMapping("member/collectionsfavoratesrela")
public class CollectionsFavoratesRelaController {
    @Autowired
    private CollectionsFavoratesRelaService collectionsFavoratesRelaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = collectionsFavoratesRelaService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{favoratesId}")
    public R info(@PathVariable("favoratesId") Integer favoratesId) {
        CollectionsFavoratesRelaEntity collectionsFavoratesRela = collectionsFavoratesRelaService.getById(favoratesId);

        return R.ok().put("collectionsFavoratesRela", collectionsFavoratesRela);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CollectionsFavoratesRelaEntity collectionsFavoratesRela) {
        collectionsFavoratesRelaService.save(collectionsFavoratesRela);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CollectionsFavoratesRelaEntity collectionsFavoratesRela) {
        collectionsFavoratesRelaService.updateById(collectionsFavoratesRela);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] favoratesIds) {
        collectionsFavoratesRelaService.removeByIds(Arrays.asList(favoratesIds));

        return R.ok();
    }

}
