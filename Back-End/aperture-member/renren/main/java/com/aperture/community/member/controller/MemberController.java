package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aperture.community.member.entity.MemberEntity;
import com.aperture.community.member.service.MemberService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;


/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */
@RestController
@RequestMapping("member/member" )
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    @RequiresPermissions("member:member:list" )
    public ResultBean list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberService.queryPage(params);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}" )
    @RequiresPermissions("member:member:info" )
    public ResultBean info(@PathVariable("id" ) Integer id) {
            MemberEntity member = memberService.getById(id);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    @RequiresPermissions("member:member:save" )
    public ResultBean<Boolean> save(@RequestBody MemberEntity member) {
            memberService.save(member);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    @RequiresPermissions("member:member:update" )
    public ResultBean<Boolean> update(@RequestBody MemberEntity member) {
            memberService.updateById(member);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    @RequiresPermissions("member:member:delete" )
    public ResultBean<Boolean> delete(@RequestBody Integer[] ids) {
            memberService.removeByIds(Arrays.asList(ids));

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

}
