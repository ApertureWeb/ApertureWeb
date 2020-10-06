package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aperture.community.member.entity.StoreArticleRelaEntity;
import com.aperture.community.member.service.StoreArticleRelaService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;



/**
 * 
 *
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 18:33:11
 */
@RestController
@RequestMapping("member/storearticlerela")
public class StoreArticleRelaController {
    @Autowired
    private StoreArticleRelaService storeArticleRelaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = storeArticleRelaService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberId}")
    public R info(@PathVariable("memberId") Long memberId){
		StoreArticleRelaEntity storeArticleRela = storeArticleRelaService.getById(memberId);

        return R.ok().put("storeArticleRela", storeArticleRela);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody StoreArticleRelaEntity storeArticleRela){
		storeArticleRelaService.save(storeArticleRela);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody StoreArticleRelaEntity storeArticleRela){
		storeArticleRelaService.updateById(storeArticleRela);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] memberIds){
		storeArticleRelaService.removeByIds(Arrays.asList(memberIds));

        return R.ok();
    }

}
