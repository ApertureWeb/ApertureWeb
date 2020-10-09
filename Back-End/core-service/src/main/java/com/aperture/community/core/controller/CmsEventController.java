package com.aperture.community.core.controller;

import com.aperture.community.core.module.CmsEventEntity;
import com.aperture.community.core.service.CmsEventService;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.entity.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @author HALOXIAO
 * @email haloxql@gmail.com
 * @date 2020-10-05 20:51:34
 */
@RestController
@RequestMapping("generator/umsevent")
public class CmsEventController {

    @Autowired
    private CmsEventService cmsEventService;

    @PostMapping("/like")
    public ResultBean<Boolean> like(Long id) {
        return null;
    }

    @PostMapping("/store")
    public ResultBean<Boolean> store(Long id) {
        return null;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{contentId}")
    public ResultBean info(@PathVariable("contentId") Long contentId) {
        ResultBean result = new ResultBean("success", RESULT_BEAN_STATUS_CODE.SUCCESS);

        return result;
    }

}
