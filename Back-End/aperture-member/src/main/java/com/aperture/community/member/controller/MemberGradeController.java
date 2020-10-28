package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aperture.community.member.entity.MemberGradeEntity;
import com.aperture.community.member.service.MemberGradeService;
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
@RequestMapping("member/membergrade")
public class MemberGradeController {
    @Autowired
    private MemberGradeService memberGradeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberGradeService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 根据memberId获取用户等级
     */
    @GetMapping("/getMemberGrade/{memberId}")
    public R getMemberGrade(@PathVariable("memberId") Long memberId){
        MemberGradeEntity memberGrade = memberGradeService.getGradeByMemberId(memberId);
        return R.ok().put("memberGrade", memberGrade);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		MemberGradeEntity memberGrade = memberGradeService.getById(id);

        return R.ok().put("memberGrade", memberGrade);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody MemberGradeEntity memberGrade){
		memberGradeService.save(memberGrade);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody MemberGradeEntity memberGrade){
		memberGradeService.updateById(memberGrade);

        return R.ok();
    }

    /**
     * 增加等级经验值
     */
    @PutMapping("/AddGradeValue/{memberId}")
    public R AddGradeValue(@PathVariable("memberId") Long memberId){
        memberGradeService.updateGradeValue(memberId);

        return R.ok();
    }

    /**
     * 用户等级升级
     */
    @PutMapping("/MemberGradeUp/{memberId}")
    public R MemberGradeUp(@PathVariable("memberId") Long memberId){
        memberGradeService.updateMemberGrade(memberId);

        return R.ok();
    }


}
