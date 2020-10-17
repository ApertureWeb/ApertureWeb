package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.vo.MemberCircleVo;
import javafx.scene.shape.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aperture.community.member.entity.MemberCircleRelaEntity;
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
     * 创建圈子
     */
    @PostMapping("/createCircle")
    public R createCircle(@RequestBody MemberCircleVo memberCircleVo){
        memberCircleRelaService.createCircle(memberCircleVo);

        return R.ok();
    }

    /**
     * 加入圈子
     */
    @PostMapping("/joinCircle")
    public R joinCircle(@RequestBody MemberCircleRelaEntity memberCircleRela){
        memberCircleRelaService.joinCircle(memberCircleRela);

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
     * 更新用户在圈子的职位
     */
    @PutMapping("/updatePositioon")
    public R updatePositioon(@RequestBody MemberCircleRelaEntity memberCircleRela){
        memberCircleRelaService.updatePositioon(memberCircleRela);
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
     * 删除圈子
     */
    @DeleteMapping("/deleteCircle/{circleId}")
    public R deleteCircle(@PathVariable("circle_id") Long circleId){
        memberCircleRelaService.removeCircle(circleId);

        return R.ok();
    }

    /**
     * 退出圈子
     */
    @DeleteMapping("/exitCircle/{memberId}/{circleId}")
    public R exitCircle(@PathVariable("memberId") Long memberId,
                        @PathVariable("circleId") Long circleId){
        memberCircleRelaService.exitCircle(memberId, circleId);

        return R.ok();
    }

}
