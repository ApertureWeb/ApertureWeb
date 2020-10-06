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

import com.aperture.community.member.entity.StoreArticleRelaEntity;
import com.aperture.community.member.service.StoreArticleRelaService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;


/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */
@RestController
@RequestMapping("member/storearticlerela" )
public class StoreArticleRelaController {
    @Autowired
    private StoreArticleRelaService storeArticleRelaService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    @RequiresPermissions("member:storearticlerela:list" )
    public ResultBean list(@RequestParam Map<String, Object> params) {
        PageUtils page = storeArticleRelaService.queryPage(params);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{memberId}" )
    @RequiresPermissions("member:storearticlerela:info" )
    public ResultBean info(@PathVariable("memberId" ) Long memberId) {
            StoreArticleRelaEntity storeArticleRela = storeArticleRelaService.getById(memberId);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    @RequiresPermissions("member:storearticlerela:save" )
    public ResultBean<Boolean> save(@RequestBody StoreArticleRelaEntity storeArticleRela) {
            storeArticleRelaService.save(storeArticleRela);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    @RequiresPermissions("member:storearticlerela:update" )
    public ResultBean<Boolean> update(@RequestBody StoreArticleRelaEntity storeArticleRela) {
            storeArticleRelaService.updateById(storeArticleRela);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    @RequiresPermissions("member:storearticlerela:delete" )
    public ResultBean<Boolean> delete(@RequestBody Long[] memberIds) {
            storeArticleRelaService.removeByIds(Arrays.asList(memberIds));

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

}
