package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aperture.community.member.entity.StoreVideoRelaEntity;
import com.aperture.community.member.service.StoreVideoRelaService;
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
@RequestMapping("member/storevideorela")
public class StoreVideoRelaController {
    @Autowired
    private StoreVideoRelaService storeVideoRelaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = storeVideoRelaService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberId}")
    public R info(@PathVariable("memberId") Long memberId){
		StoreVideoRelaEntity storeVideoRela = storeVideoRelaService.getById(memberId);

        return R.ok().put("storeVideoRela", storeVideoRela);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody StoreVideoRelaEntity storeVideoRela){
		storeVideoRelaService.save(storeVideoRela);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody StoreVideoRelaEntity storeVideoRela){
		storeVideoRelaService.updateById(storeVideoRela);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] memberIds){
		storeVideoRelaService.removeByIds(Arrays.asList(memberIds));

        return R.ok();
    }

}
