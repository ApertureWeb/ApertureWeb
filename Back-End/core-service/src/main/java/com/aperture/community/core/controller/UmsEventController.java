package com.aperture.community.core.controller;

import com.aperture.community.core.module.UmsEventEntity;
import com.aperture.community.core.service.UmsEventService;
import com.aperture.community.entity.PageResult;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.entity.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * @author HALOXIAO
 * @email haloxql@gmail.com
 * @date 2020-10-05 20:51:34
 */
@RestController
@RequestMapping("generator/umsevent" )
public class UmsEventController {
    @Autowired
    private UmsEventService umsEventService;

    /**
     * 列表
     */
    @RequestMapping("/list" )
    public ResultBean list(@RequestParam Map<String, Object> params) {
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{contentId}" )
    public ResultBean info(@PathVariable("contentId" ) Long contentId) {
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }

    /**
     * 保存
     */
    @RequestMapping("/save" )
    public ResultBean<Boolean> save(@RequestBody UmsEventEntity umsEvent) {

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 修改
     */
    @RequestMapping("/update" )
    public ResultBean<Boolean> update(@RequestBody UmsEventEntity umsEvent) {

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete" )
    public ResultBean<Boolean> delete(@RequestBody Long[] contentIds) {

        return new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
    }

}
