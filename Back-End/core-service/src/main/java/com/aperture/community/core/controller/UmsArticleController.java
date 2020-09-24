package com.aperture.community.core.controller;


import com.aperture.community.common.standard.code.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.common.standard.response.ResultBean;
import com.aperture.community.core.module.param.UmsArticleParam;
import com.aperture.community.core.module.validation.ValidationGroup;
import com.aperture.community.core.service.IUmsArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-09-23
 */
@RestController("/api/v1/article")
public class UmsArticleController {

    @Autowired
    IUmsArticleService umsArticleService;

    @PostMapping
    public ResultBean<Integer> uploadArticle(@RequestBody @Validated({ValidationGroup.addGroup.class}) UmsArticleParam umsArticleParam) {
        ResultBean<Integer> bean = new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
        bean.setData(umsArticleService.save(umsArticleParam));
        return bean;
    }




}

