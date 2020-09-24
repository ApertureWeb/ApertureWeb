package com.aperture.community.core.controller;

import com.aperture.community.common.standard.response.ResultBean;
import com.aperture.community.core.module.param.UmsTagParam;
import com.aperture.community.core.module.vo.UmsTagVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UmsTagController {

    @DeleteMapping
    public ResultBean<Boolean> delete(Long id) {
        return null;
    }

    @GetMapping
    public ResultBean<UmsTagVO> search(@RequestBody @Valid UmsTagParam umsTagParam) {
        return null;
    }

    @PostMapping
    public ResultBean<Integer> save(@RequestBody @Valid UmsTagParam umsTagParam) {
        return null;
    }

    @PutMapping
    public ResultBean<Boolean> update(@RequestBody @Valid UmsTagParam umsTagParam) {
        return null;
    }

}