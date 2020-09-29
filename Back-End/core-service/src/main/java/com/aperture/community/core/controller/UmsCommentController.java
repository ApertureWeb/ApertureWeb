package com.aperture.community.core.controller;

import com.aperture.community.common.standard.response.ResultBean;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsCommentParam;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.UmsCommentVO;
import com.aperture.community.core.service.IUmsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/api/v1")
public class UmsCommentController {


    @Autowired
    IUmsCommentService umsCommentService;

    @DeleteMapping("/")
    public ResultBean<Boolean> delete(Long id) {
        return null;
    }

    @GetMapping
    public ResultBean<UmsCommentVO> search(@RequestBody @Valid UmsCommentParam umsCommentParam) {
        return null;
    }

    @PostMapping
    public ResultBean<Integer> save(@RequestBody @Valid UmsCommentParam umsCommentParam) {
        return null;
    }

    @PutMapping
    public ResultBean<Boolean> update(@RequestBody @Valid UmsCommentParam umsCommentParam) {
        return null;
    }

    @GetMapping("/article/${articleId}/comment")
    public ResultBean<PageVO<UmsCommentVO>> pageComment(@PathVariable Integer articleId, @RequestBody @Valid PageParam pageParam) {
        umsCommentService.listPage(pageParam, articleId);


        return null;
    }

}