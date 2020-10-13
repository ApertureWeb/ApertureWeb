package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aperture.community.member.entity.FollowEntity;
import com.aperture.community.member.service.FollowService;
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
@RequestMapping("member/follow")
public class FollowController {
    @Autowired
    private FollowService followService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = followService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		FollowEntity follow = followService.getById(id);

        return R.ok().put("follow", follow);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FollowEntity follow){
		followService.save(follow);

        return R.ok();
    }

    /**
     * 添加关注
     */
    @RequestMapping("/saveFollow")
    public R saveFollow(@RequestBody FollowEntity follow){
        followService.saveFollow(follow);

        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody FollowEntity follow){
		followService.updateById(follow);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		followService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
