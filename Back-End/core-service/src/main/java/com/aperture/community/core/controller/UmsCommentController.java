package com.aperture.community.core.controller;

import com.aperture.community.core.module.param.UmsReplyParam;
import com.aperture.community.standard.code.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.standard.response.ResultBean;
import com.aperture.community.core.common.ContentType;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsCommentParam;
import com.aperture.community.core.module.validation.ValidationGroup;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.UmsCommentVO;
import com.aperture.community.core.service.IUmsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/api/v1")
public class UmsCommentController {


    @Autowired
    IUmsCommentService umsCommentService;

    @DeleteMapping("/article/${articleId}/comment")
    public ResultBean<Boolean> deleteArticleComment(@PathVariable("articleId") Long id) {
        return null;
    }


    @DeleteMapping("/video/${videoId}/comment")
    public ResultBean<Boolean> deleteVideoComment(@PathVariable("videoId") Long videoId, @RequestParam("articleId") Long articleId) {
        umsCommentService.delete(articleId);
        return null;
    }

    @PostMapping("/comment/reply")
    public ResultBean<Boolean> sendReply(@RequestBody @Validated(ValidationGroup.addGroup.class) UmsReplyParam umsReplyParam) {
        try {
            umsCommentService.sendReply(umsReplyParam);
        } catch (java.rmi.RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }


    @GetMapping
    public ResultBean<UmsCommentVO> search(@RequestBody @Valid UmsCommentParam umsCommentParam) {
        return null;
    }

    @PostMapping("/article")
    public ResultBean<Integer> sendArticleComment(@RequestBody @Validated(ValidationGroup.addGroup.class) UmsCommentParam umsCommentParam) {
        umsCommentService.sendComment(umsCommentParam, ContentType.ARTICLE);
        return null;
    }

    @PostMapping("/video")
    public ResultBean<Integer> sendVideoComment(@RequestBody @Validated(ValidationGroup.addGroup.class) UmsCommentParam umsCommentParam) {
        umsCommentService.sendComment(umsCommentParam, ContentType.VIDEO);
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