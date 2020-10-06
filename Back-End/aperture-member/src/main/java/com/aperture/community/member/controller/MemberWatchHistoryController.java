package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aperture.community.member.entity.MemberWatchHistoryEntity;
import com.aperture.community.member.service.MemberWatchHistoryService;
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
@RequestMapping("member/memberwatchhistory")
public class MemberWatchHistoryController {
    @Autowired
    private MemberWatchHistoryService memberWatchHistoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberWatchHistoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		MemberWatchHistoryEntity memberWatchHistory = memberWatchHistoryService.getById(id);

        return R.ok().put("memberWatchHistory", memberWatchHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MemberWatchHistoryEntity memberWatchHistory){
		memberWatchHistoryService.save(memberWatchHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MemberWatchHistoryEntity memberWatchHistory){
		memberWatchHistoryService.updateById(memberWatchHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		memberWatchHistoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
