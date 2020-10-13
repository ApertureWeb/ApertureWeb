package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.aperture.community.member.vo.FavoratesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aperture.community.member.entity.FavoratesEntity;
import com.aperture.community.member.service.FavoratesService;
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
    public R info(@PathVariable("id") Long id){
		FavoratesEntity favorates = favoratesService.getById(id);

        return R.ok().put("favorates", favorates);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save( @RequestBody FavoratesEntity favoratesEntity){
        favoratesService.save(favoratesEntity);
        return R.ok();
    }

    /**
     * 新增收藏夹
     */
    @PostMapping("/saveFavorates")
    public R saveFavorates( @RequestBody FavoratesVo favoratesVo){
		favoratesService.saveFavorates(favoratesVo);
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
    public R delete(@RequestBody Long[] ids){
		favoratesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
