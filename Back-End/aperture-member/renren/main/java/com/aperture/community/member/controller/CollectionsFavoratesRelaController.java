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

import com.aperture.community.member.entity.CollectionsFavoratesRelaEntity;
import com.aperture.community.member.service.CollectionsFavoratesRelaService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;


/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */
@RestController
@RequestMapping("member/collectionsfavoratesrela" )
public class CollectionsFavoratesRelaController {
    @Autowired
    private CollectionsFavoratesRelaService collectionsFavoratesRelaService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    @RequiresPermissions("member:collectionsfavoratesrela:list" )
    public ResultBean list(@RequestParam Map<String, Object> params) {
        PageUtils page = collectionsFavoratesRelaService.queryPage(params);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{favoratesId}" )
    @RequiresPermissions("member:collectionsfavoratesrela:info" )
    public ResultBean info(@PathVariable("favoratesId" ) Integer favoratesId) {
            CollectionsFavoratesRelaEntity collectionsFavoratesRela = collectionsFavoratesRelaService.getById(favoratesId);
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    @RequiresPermissions("member:collectionsfavoratesrela:save" )
    public ResultBean<Boolean> save(@RequestBody CollectionsFavoratesRelaEntity collectionsFavoratesRela) {
            collectionsFavoratesRelaService.save(collectionsFavoratesRela);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    @RequiresPermissions("member:collectionsfavoratesrela:update" )
    public ResultBean<Boolean> update(@RequestBody CollectionsFavoratesRelaEntity collectionsFavoratesRela) {
            collectionsFavoratesRelaService.updateById(collectionsFavoratesRela);

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    @RequiresPermissions("member:collectionsfavoratesrela:delete" )
    public ResultBean<Boolean> delete(@RequestBody Integer[] favoratesIds) {
            collectionsFavoratesRelaService.removeByIds(Arrays.asList(favoratesIds));

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

}
