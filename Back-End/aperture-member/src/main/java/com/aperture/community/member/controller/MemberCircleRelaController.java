package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.aperture.community.member.model.MemberCircleRelaEntity;
import com.aperture.community.member.model.MemberEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.MemberCircleRelaParam;
import com.aperture.community.member.model.vo.MemberCircleRelaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aperture.community.member.service.MemberCircleRelaService;
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
@RequestMapping("member/membercirclerela")
public class MemberCircleRelaController {

    @Autowired
    private MemberCircleRelaService memberCircleRelaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberCircleRelaService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		MemberCircleRelaEntity memberCircleRela = memberCircleRelaService.getById(id);
        return R.ok().put("memberCircleRela", memberCircleRela);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody MemberCircleRelaEntity memberCircleRela){
        memberCircleRelaService.save(memberCircleRela);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody MemberCircleRelaEntity memberCircleRela){
        memberCircleRelaService.updateById(memberCircleRela);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        memberCircleRelaService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 根据memberId获取用户相关圈子信息
     */
    @GetMapping("/getMemberCircleInfo/{memberId}")
    public R getMemberCircleInfo(@PathVariable("memberId") Long memberId){
        MessageDto<MemberCircleRelaEntity> memberCircleRelaEntity = memberCircleRelaService.getMemberCircleInfo(memberId);
        return R.ok().put("memberCircleRelaEntity", memberCircleRelaEntity.getData());
    }


    /**
     * 获取当前圈子与用户的关系
     */
    @GetMapping("/getMemberCircleRela/{memberId}")
    public R getMemberCircleRela(@PathVariable("memberId") Long memberId){
        MessageDto<MemberCircleRelaVo> memberCircleBaseInfo = memberCircleRelaService.getMemberCircleRela(memberId);
        return R.ok().put("memberCircleBaseInfo", memberCircleBaseInfo.getData());
    }



}
