package com.aperture.community.core.controller;


import com.aperture.community.common.standard.code.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.common.standard.response.ResultBean;
import com.aperture.community.core.module.param.UmsArticleParam;
import com.aperture.community.core.module.validation.ValidationGroup;
import com.aperture.community.core.service.IUmsArticleService;
import com.aperture.community.core.service.impl.UmsArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RestController("/asdas/")
public class UmsArticleController {

    @Autowired
    UmsArticleServiceImpl umsArticleService;

    @PostMapping
    public ResultBean<Integer> saveArticle(@RequestBody @Validated({ValidationGroup.addGroup.class}) UmsArticleParam umsArticleParam) {
        ResultBean<Integer> bean = new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
        //bean.setData(umsArticleService.save(umsArticleParam));
        return bean;
    }

    @DeleteMapping
    public ResultBean<Boolean> deleteArticle(Long id) {
        return null;
    }


}

