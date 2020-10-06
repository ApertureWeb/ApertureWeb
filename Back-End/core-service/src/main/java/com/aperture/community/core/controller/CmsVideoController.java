package com.aperture.community.core.controller;


import com.aperture.community.core.module.param.CmsVideoParam;
import com.aperture.community.core.service.impl.CmsVideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CmsVideoController {

    @Autowired
    CmsVideoServiceImpl umsVideoService;

    @PostMapping("/wwasc")
    public boolean uploadArticle(@RequestBody CmsVideoParam cmsVideoParam) {

        return false;
    }


}

