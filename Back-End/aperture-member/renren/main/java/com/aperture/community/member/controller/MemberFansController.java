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

import com.aperture.community.member.entity.MemberFansEntity;
import com.aperture.community.member.service.MemberFansService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;


/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */
@RestController
@RequestMapping("member/memberfans" )
public class MemberFansController {
    @Autowired
    private MemberFansService memberFansService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    @RequiresPermissions("member:memberfans:list" )
    public ResultBean list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberFansService.queryPage(params);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{fansId}" )
    @RequiresPermissions("member:memberfans:info" )
    public ResultBean info(@PathVariable("fansId" ) Integer fansId) {
            MemberFansEntity memberFans = memberFansService.getById(fansId);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    @RequiresPermissions("member:memberfans:save" )
    public ResultBean<Boolean> save(@RequestBody MemberFansEntity memberFans) {
            memberFansService.save(memberFans);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    @RequiresPermissions("member:memberfans:update" )
    public ResultBean<Boolean> update(@RequestBody MemberFansEntity memberFans) {
            memberFansService.updateById(memberFans);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    @RequiresPermissions("member:memberfans:delete" )
    public ResultBean<Boolean> delete(@RequestBody Integer[] fansIds) {
            memberFansService.removeByIds(Arrays.asList(fansIds));

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

}
