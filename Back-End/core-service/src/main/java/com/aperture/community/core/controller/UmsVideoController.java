package com.aperture.community.core.controller;


import com.aperture.community.core.module.param.UmsVideoParam;
import com.aperture.community.core.service.IUmsVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

@RestController
public class UmsVideoController {

    @Autowired
    IUmsVideoService umsVideoService;

    @PostMapping
    public boolean uploadArticle(@RequestBody UmsVideoParam umsVideoParam) {

        return false;
    }


}
