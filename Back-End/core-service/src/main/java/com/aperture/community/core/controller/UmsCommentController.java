package com.aperture.community.core.controller;

import com.aperture.community.common.standard.response.ResultBean;
import com.aperture.community.core.module.param.UmsCommentParam;
import com.aperture.community.core.module.vo.UmsCommentVO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class  UmsCommentController {

    @DeleteMapping
    public ResultBean<Boolean> delete(Long id) {
        return null;
    }

    @GetMapping
    public ResultBean<UmsCommentVO> search(@RequestBody @Valid UmsCommentParam UmsCommentParam) {
        return null;
    }

    @PostMapping
    public ResultBean<Integer> save(@RequestBody @Valid UmsCommentParam UmsCommentParam ) {
        return null;
    }

    @PutMapping
    public ResultBean<Boolean> update(@RequestBody @Valid UmsCommentParam UmsCommentParam)  {
        return null;
    }

}