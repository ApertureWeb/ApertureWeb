package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aperture.community.member.entity.FollowGroupEntity;
import com.aperture.community.member.service.FollowGroupService;
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
@RequestMapping("member/followgroup")
public class FollowGroupController {
    @Autowired
    private FollowGroupService followGroupService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = followGroupService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		FollowGroupEntity followGroup = followGroupService.getById(id);

        return R.ok().put("followGroup", followGroup);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody FollowGroupEntity followGroup){
		followGroupService.save(followGroup);

        return R.ok();
    }

    /**
     * 添加关注
     */
    @PostMapping("/saveFollowGroup")
    public R saveFollowGroup(@RequestBody FollowGroupEntity followGroup){
        followGroupService.saveFollowGroup(followGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody FollowGroupEntity followGroup){
		followGroupService.updateById(followGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		followGroupService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 删除关注分组
     */
    @DeleteMapping("/deleteFollowGroup")
    public R deleteFollowGroup(@RequestParam Long id){
        followGroupService.removeFollowGroup(id);

        return R.ok();
    }

}
