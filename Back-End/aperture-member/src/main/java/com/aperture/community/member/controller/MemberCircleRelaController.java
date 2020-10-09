package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aperture.community.member.entity.MemberCircleRelaEntity;
import com.aperture.community.member.service.MemberCircleRelaService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;


/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-09 13:01:14
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
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberCircleRelaService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        MemberCircleRelaEntity memberCircleRela = memberCircleRelaService.getById(id);

        return R.ok().put("memberCircleRela", memberCircleRela);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MemberCircleRelaEntity memberCircleRela) {
        memberCircleRelaService.save(memberCircleRela);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MemberCircleRelaEntity memberCircleRela) {
        memberCircleRelaService.updateById(memberCircleRela);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        memberCircleRelaService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
