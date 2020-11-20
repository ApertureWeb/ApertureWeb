package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.aperture.community.member.model.FollowGroupRelaEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.FollowGroupRelaParam;
import com.aperture.community.member.model.validation.ValidationGroup;
import com.aperture.community.member.model.vo.FollowGroupRelaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.aperture.community.member.service.FollowGroupRelaService;
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
@RequestMapping("member/followGroupRela")
public class FollowGroupRelaController {
    @Autowired
    private FollowGroupRelaService followGroupRelaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = followGroupRelaService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        FollowGroupRelaEntity followGroupRelaEntity = followGroupRelaService.getById(id);

        return R.ok().put("follow", followGroupRelaEntity);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody FollowGroupRelaEntity follow){
		followGroupRelaService.save(follow);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody FollowGroupRelaEntity follow){
        followGroupRelaService.updateById(follow);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        followGroupRelaService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 查询该用户的关注组列表
     */
    @GetMapping("/getFollowGroupList/{memberId}")
    public R getFollowGroupList(@PathVariable("memberId") Long memberId){
        MessageDto<List<FollowGroupRelaVo>> followGroupList = followGroupRelaService.getFollowGroupList(memberId);
        return R.ok().put("followGroupList", followGroupList);
    }

    /**
     * 用户添加分组
     */
    @PostMapping("/addFollowGroup/{memberId}")
    public R addFollowGroup(@PathVariable("memberId") Long memberId,
                        @RequestBody @Validated(ValidationGroup.addGroup.class) FollowGroupRelaParam followGroupRelaParam){
        followGroupRelaService.addFollowGroup(memberId, followGroupRelaParam);
        return R.ok();
    }

    /**
     * 用户删除分组
     */
    @DeleteMapping("/removeFollowGroup/{memberId}")
    public R removeFollowGroup(@PathVariable("memberId") Long memberId,
                               @RequestParam Long groupId){
        followGroupRelaService.removeFollowGroup(memberId, groupId);
        return R.ok();
    }

    /**
     * 分组下的关注数量+1
     */
    @PutMapping("/addFollowCount")
    public R addFollowCount(@RequestParam Long groupId){
        followGroupRelaService.addFollowCount(groupId);
        return R.ok();
    }

    /**
     * 分组下的关注数量-1
     */
    @PutMapping("/subFollowCount")
    public R subFollowCount(@RequestParam Long groupId){
        followGroupRelaService.subFollowCount(groupId);
        return R.ok();
    }

    /**
     * 查询该用户的默认分组的分组id
     */
    @GetMapping("/getDefaultGroupId/{memberId}")
    public R getDefaultGroupId(@PathVariable("memberId") Long memberId){
        followGroupRelaService.getDefaultGroupId(memberId);
        return R.ok();
    }

    /**
     * 查询该用户的特别关注分组的分组id
     */
    @PutMapping("/getSpecialGroupId/{memberId}")
    public R getSpecialGroupId(@PathVariable("memberId") Long memberId){
        followGroupRelaService.getSpecialGroupId(memberId);
        return R.ok();
    }



}
