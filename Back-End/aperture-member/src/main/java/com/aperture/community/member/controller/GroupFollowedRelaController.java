package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.aperture.community.member.model.GroupFollowedRelaEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.GroupFollowedRelaParam;
import com.aperture.community.member.model.validation.ValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.aperture.community.member.service.GroupFollowedRelaService;
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
@RequestMapping("member/groupFollowRela")
public class GroupFollowedRelaController {

    @Autowired
    private GroupFollowedRelaService groupFollowedRelaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = groupFollowedRelaService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody GroupFollowedRelaEntity followGroup){
        groupFollowedRelaService.updateById(followGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        groupFollowedRelaService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		GroupFollowedRelaEntity followGroup = groupFollowedRelaService.getById(id);

        return R.ok().put("followGroup", followGroup);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody GroupFollowedRelaEntity followGroup){
        groupFollowedRelaService.save(followGroup);

        return R.ok();
    }

    /**
     * 查询关注组下的关注列表
     */
    @GetMapping("/getGroupFollowList/{memberId}")
    public R getGroupFollowList(@PathVariable("memberId") Long memberId){
        MessageDto<List<GroupFollowedRelaEntity>> groupFollowedList = groupFollowedRelaService.getGroupFollowList(memberId);
        return R.ok().put("groupFollowedList", groupFollowedList);
    }

    /**
     * 添加关注
     */
    @PostMapping("/addFollow/{memberId}")
    public R addFollow(@PathVariable("memberId") Long memberId,
            @RequestBody @Validated(ValidationGroup.addGroup.class) GroupFollowedRelaParam groupFollowedRelaParam){
        groupFollowedRelaService.addFollow(memberId, groupFollowedRelaParam);
        return R.ok();
    }

    /**
     * 取消关注
     */
    @DeleteMapping("/cancelFollow/{memberId}")
    public R cancelFollow(@PathVariable("memberId") Long memberId,
                       @RequestBody @Validated(ValidationGroup.deleteGroup.class) GroupFollowedRelaParam groupFollowedRelaParam){
        groupFollowedRelaService.cancelFollow(memberId, groupFollowedRelaParam);
        return R.ok();
    }

    /**
     * 批量设置关注用户到分组
     */
    @PutMapping("/setFollowtoGroup/{memberId}")
    public R setFollowtoGroup(@PathVariable("memberId") Long memberId,
                       @RequestBody @Validated(ValidationGroup.updateGroup.class) GroupFollowedRelaParam groupFollowedRelaParam){
        groupFollowedRelaService.setFollowtoGroup(memberId, groupFollowedRelaParam);
        return R.ok();
    }

}
