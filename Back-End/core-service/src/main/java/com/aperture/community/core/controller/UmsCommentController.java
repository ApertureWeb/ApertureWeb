package com.aperture.community.core.controller;

import com.aperture.community.common.standard.code.RESULT_BEAN_STATUS_CODE;
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


    /**
     * 获取首页评论类型,按时间排行
     */
    @GetMapping("/article/${articleId}/comment/like")
    public ResultBean<PageVO<UmsCommentVO>> pageComment(@PathVariable Integer articleId, @RequestBody @Valid PageParam pageParam) {
        PageVO<UmsCommentVO> pageVO = umsCommentService.listPage(pageParam, articleId, true);
        ResultBean<PageVO<UmsCommentVO>> result = new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
        result.setData(pageVO);
        return result;
    }

    /**
     * 获取首页评论类型,按热度排行
     */
    @GetMapping("/article/${articleId}/comment/time")
    public ResultBean<PageVO<UmsCommentVO>> pageCommentHeat(@PathVariable Integer articleId, @RequestBody @Valid PageParam pageParam) {
        PageVO<UmsCommentVO> pageVO = umsCommentService.listPage(pageParam, articleId, false);
        ResultBean<PageVO<UmsCommentVO>> result = new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
        result.setData(pageVO);
        return result;
    }

}