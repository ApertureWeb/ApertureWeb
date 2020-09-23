package com.aperture.community.core.controller;


import com.alibaba.druid.stat.JdbcResultSetStatMBean;
import com.aperture.community.common.standard.response.ResultBean;
import com.aperture.community.core.module.param.UmsArticleParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping
    public ResultBean<Integer> uploadArticle(@RequestBody UmsArticleParam umsArticleParam) {
        return null;
    }


}

