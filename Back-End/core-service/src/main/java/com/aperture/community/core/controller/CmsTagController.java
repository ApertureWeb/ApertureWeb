package com.aperture.community.core.controller;

import com.aperture.community.core.module.param.CmsTagParam;
import com.aperture.community.core.module.vo.CmsTagVO;
import com.aperture.community.core.service.CmsTagService;
import com.aperture.community.entity.ResultBean;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/tag")
public class CmsTagController {

    private CmsTagService cmsTagService;

    @DeleteMapping
    public ResultBean<Boolean> delete(Long contentId, Long id) {
        cmsTagService.delete(id, contentId);
        return null;
    }

    @GetMapping
    public ResultBean<CmsTagVO> search(@RequestBody @Valid CmsTagParam cmsTagParam) {
        return null;
    }

    @PostMapping
    public ResultBean<Integer> save(@RequestBody @Valid CmsTagParam cmsTagParam) {

        return null;
    }


}