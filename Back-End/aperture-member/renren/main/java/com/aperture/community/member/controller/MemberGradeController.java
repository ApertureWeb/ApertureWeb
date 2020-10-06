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

import com.aperture.community.member.entity.MemberGradeEntity;
import com.aperture.community.member.service.MemberGradeService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;


/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */
@RestController
@RequestMapping("member/membergrade" )
public class MemberGradeController {
    @Autowired
    private MemberGradeService memberGradeService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    @RequiresPermissions("member:membergrade:list" )
    public ResultBean list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberGradeService.queryPage(params);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}" )
    @RequiresPermissions("member:membergrade:info" )
    public ResultBean info(@PathVariable("id" ) Integer id) {
            MemberGradeEntity memberGrade = memberGradeService.getById(id);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    @RequiresPermissions("member:membergrade:save" )
    public ResultBean<Boolean> save(@RequestBody MemberGradeEntity memberGrade) {
            memberGradeService.save(memberGrade);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    @RequiresPermissions("member:membergrade:update" )
    public ResultBean<Boolean> update(@RequestBody MemberGradeEntity memberGrade) {
            memberGradeService.updateById(memberGrade);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    @RequiresPermissions("member:membergrade:delete" )
    public ResultBean<Boolean> delete(@RequestBody Integer[] ids) {
            memberGradeService.removeByIds(Arrays.asList(ids));

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

}
