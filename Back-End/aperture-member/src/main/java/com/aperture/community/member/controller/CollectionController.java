package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.aperture.community.member.entity.CollectionEntity;
import com.aperture.community.member.vo.CollectionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aperture.community.member.service.CollectionService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;



/**
 * 
 *
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-11 19:26:10
 */
@RestController
@RequestMapping("member/collcetions")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = collectionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CollectionEntity collcetions = collectionService.getById(id);

        return R.ok().put("collcetions", collcetions);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CollectionEntity collectionEntity){
        collectionService.save(collectionEntity);

        return R.ok();
    }

    /**
     * 添加收藏
     */
    @RequestMapping("/saveCollection")
    public R saveCollection(@RequestBody CollectionVo vo){
        collectionService.saveCollection(vo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CollectionEntity collectionEntity){
        collectionService.updateById(collectionEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        collectionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
