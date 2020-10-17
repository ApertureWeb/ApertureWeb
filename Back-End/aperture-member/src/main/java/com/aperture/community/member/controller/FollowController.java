package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.aperture.community.member.vo.FollowCopyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/save")
    public R save(@RequestBody FollowEntity follow){
		followService.save(follow);

        return R.ok();
    }

    /**
     * 添加关注
     */
    @PostMapping("/saveFollow")
    public R saveFollow(@RequestBody FollowEntity follow){
        followService.saveFollow(follow);

        return R.ok();
    }

    /**
     * 设置分组(关注复制)
     */
    @PostMapping("/copyFollow/{memberId}")
    public R copyFollow(@PathVariable("memberId") Long memberId,
                        @RequestBody FollowCopyVo followCopyVo){
        followService.updateFollow(memberId, followCopyVo);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody FollowEntity follow){
		followService.updateById(follow);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		followService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 取消关注
     */
    @DeleteMapping("/cancelFollow")
    public R cancelFollow(@RequestParam Long id){
        followService.removeFollow(id);

        return R.ok();
    }

}
