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

import com.aperture.community.member.entity.MemberFollowEntity;
import com.aperture.community.member.service.MemberFollowService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;


/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */
@RestController
@RequestMapping("member/memberfollow" )
public class MemberFollowController {
    @Autowired
    private MemberFollowService memberFollowService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    @RequiresPermissions("member:memberfollow:list" )
    public ResultBean list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberFollowService.queryPage(params);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberId}" )
    @RequiresPermissions("member:memberfollow:info" )
    public ResultBean info(@PathVariable("memberId" ) Long memberId) {
            MemberFollowEntity memberFollow = memberFollowService.getById(memberId);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    @RequiresPermissions("member:memberfollow:save" )
    public ResultBean<Boolean> save(@RequestBody MemberFollowEntity memberFollow) {
            memberFollowService.save(memberFollow);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    @RequiresPermissions("member:memberfollow:update" )
    public ResultBean<Boolean> update(@RequestBody MemberFollowEntity memberFollow) {
            memberFollowService.updateById(memberFollow);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    @RequiresPermissions("member:memberfollow:delete" )
    public ResultBean<Boolean> delete(@RequestBody Long[] memberIds) {
            memberFollowService.removeByIds(Arrays.asList(memberIds));

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

}
