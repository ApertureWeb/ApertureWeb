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

import com.aperture.community.member.entity.StoreVideoRelaEntity;
import com.aperture.community.member.service.StoreVideoRelaService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;


/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */
@RestController
@RequestMapping("member/storevideorela" )
public class StoreVideoRelaController {
    @Autowired
    private StoreVideoRelaService storeVideoRelaService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    @RequiresPermissions("member:storevideorela:list" )
    public ResultBean list(@RequestParam Map<String, Object> params) {
        PageUtils page = storeVideoRelaService.queryPage(params);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberId}" )
    @RequiresPermissions("member:storevideorela:info" )
    public ResultBean info(@PathVariable("memberId" ) Long memberId) {
            StoreVideoRelaEntity storeVideoRela = storeVideoRelaService.getById(memberId);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    @RequiresPermissions("member:storevideorela:save" )
    public ResultBean<Boolean> save(@RequestBody StoreVideoRelaEntity storeVideoRela) {
            storeVideoRelaService.save(storeVideoRela);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    @RequiresPermissions("member:storevideorela:update" )
    public ResultBean<Boolean> update(@RequestBody StoreVideoRelaEntity storeVideoRela) {
            storeVideoRelaService.updateById(storeVideoRela);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    @RequiresPermissions("member:storevideorela:delete" )
    public ResultBean<Boolean> delete(@RequestBody Long[] memberIds) {
            storeVideoRelaService.removeByIds(Arrays.asList(memberIds));

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

}
