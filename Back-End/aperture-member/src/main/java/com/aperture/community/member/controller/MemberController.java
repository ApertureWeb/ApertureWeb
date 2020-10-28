package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.aperture.community.member.vo.rspVo.MemberBaseInfoRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aperture.community.member.entity.MemberEntity;
import com.aperture.community.member.service.MemberService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;



/**
 * 
 *
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-12 21:32:49
 */
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }


    /**
     * 信息
     */
    @GetMapping("/getMemberBaseInfo/{memberId}")
    public R getMemberBaseInfo(@PathVariable("memberId") Long memberId){
        MemberBaseInfoRespVo memberBaseInfo = memberService.getMemberBaseInfo(memberId);

        return R.ok().put("memberBaseInfo", memberBaseInfo);
    }


    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody MemberEntity member){
		memberService.save(member);

        return R.ok();
    }

    /**
     * 新增用户
     */
    @PostMapping("/saveMemberInfo")
    public R saveMemberInfo(@RequestBody MemberEntity member){
        memberService.saveMemberInfo(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/updateMemberInfo")
    public R updateMemberInfo(@RequestBody MemberEntity member){
        memberService.updateMemberInfo(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
