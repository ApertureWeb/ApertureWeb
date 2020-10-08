package com.aperture.community.core.controller;

import com.aperture.community.core.module.param.CmsTagParam;
import com.aperture.community.core.module.vo.CmsTagVO;
import com.aperture.community.entity.ResultBean;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/tag")
public class CmsTagController {




    @DeleteMapping
    public ResultBean<Boolean> delete(Long id) {
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

    @PutMapping
    public ResultBean<Boolean> update(@RequestBody @Valid CmsTagParam cmsTagParam) {
        return null;
    }

}