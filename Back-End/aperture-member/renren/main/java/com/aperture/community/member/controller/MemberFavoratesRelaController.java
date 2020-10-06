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

import com.aperture.community.member.entity.MemberFavoratesRelaEntity;
import com.aperture.community.member.service.MemberFavoratesRelaService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;


/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */
@RestController
@RequestMapping("member/memberfavoratesrela" )
public class MemberFavoratesRelaController {
    @Autowired
    private MemberFavoratesRelaService memberFavoratesRelaService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    @RequiresPermissions("member:memberfavoratesrela:list" )
    public ResultBean list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberFavoratesRelaService.queryPage(params);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberId}" )
    @RequiresPermissions("member:memberfavoratesrela:info" )
    public ResultBean info(@PathVariable("memberId" ) Integer memberId) {
            MemberFavoratesRelaEntity memberFavoratesRela = memberFavoratesRelaService.getById(memberId);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    @RequiresPermissions("member:memberfavoratesrela:save" )
    public ResultBean<Boolean> save(@RequestBody MemberFavoratesRelaEntity memberFavoratesRela) {
            memberFavoratesRelaService.save(memberFavoratesRela);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    @RequiresPermissions("member:memberfavoratesrela:update" )
    public ResultBean<Boolean> update(@RequestBody MemberFavoratesRelaEntity memberFavoratesRela) {
            memberFavoratesRelaService.updateById(memberFavoratesRela);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    @RequiresPermissions("member:memberfavoratesrela:delete" )
    public ResultBean<Boolean> delete(@RequestBody Integer[] memberIds) {
            memberFavoratesRelaService.removeByIds(Arrays.asList(memberIds));

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

}
