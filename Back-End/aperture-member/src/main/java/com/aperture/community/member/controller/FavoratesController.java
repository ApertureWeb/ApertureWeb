package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

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
 * 
 *
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 18:33:11
 */
@RestController
@RequestMapping("member/favorates")
public class FavoratesController {
    @Autowired
    private FavoratesService favoratesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = favoratesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		FavoratesEntity favorates = favoratesService.getById(id);

        return R.ok().put("favorates", favorates);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FavoratesEntity favorates){
		favoratesService.save(favorates);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody FavoratesEntity favorates){
		favoratesService.updateById(favorates);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		favoratesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
