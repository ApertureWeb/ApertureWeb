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

import com.aperture.community.member.entity.MemberWatchHistoryRelaEntity;
import com.aperture.community.member.service.MemberWatchHistoryRelaService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;


/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */
@RestController
@RequestMapping("member/memberwatchhistoryrela" )
public class MemberWatchHistoryRelaController {
    @Autowired
    private MemberWatchHistoryRelaService memberWatchHistoryRelaService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    @RequiresPermissions("member:memberwatchhistoryrela:list" )
    public ResultBean list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberWatchHistoryRelaService.queryPage(params);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberId}" )
    @RequiresPermissions("member:memberwatchhistoryrela:info" )
    public ResultBean info(@PathVariable("memberId" ) Integer memberId) {
            MemberWatchHistoryRelaEntity memberWatchHistoryRela = memberWatchHistoryRelaService.getById(memberId);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    @RequiresPermissions("member:memberwatchhistoryrela:save" )
    public ResultBean<Boolean> save(@RequestBody MemberWatchHistoryRelaEntity memberWatchHistoryRela) {
            memberWatchHistoryRelaService.save(memberWatchHistoryRela);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    @RequiresPermissions("member:memberwatchhistoryrela:update" )
    public ResultBean<Boolean> update(@RequestBody MemberWatchHistoryRelaEntity memberWatchHistoryRela) {
            memberWatchHistoryRelaService.updateById(memberWatchHistoryRela);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    @RequiresPermissions("member:memberwatchhistoryrela:delete" )
    public ResultBean<Boolean> delete(@RequestBody Integer[] memberIds) {
            memberWatchHistoryRelaService.removeByIds(Arrays.asList(memberIds));

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

}
