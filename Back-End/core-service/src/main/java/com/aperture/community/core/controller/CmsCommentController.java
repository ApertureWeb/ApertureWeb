package com.aperture.community.core.controller;

import com.aperture.community.core.common.status.ContentType;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.CmsCommentParam;
import com.aperture.community.core.module.validation.ValidationGroup;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.CmsCommentVO;
import com.aperture.community.core.service.CmsCommentService;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.entity.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/api/v1")
public class CmsCommentController {


    @Autowired
    CmsCommentService cmsCommentService;


    @DeleteMapping("/article/${articleId}/comment")
    public ResultBean<Boolean> deleteArticleComment(@PathVariable("articleId") Long id) {
        return null;
    }


    @DeleteMapping("/video/${videoId}/comment")
    public ResultBean<Boolean> deleteVideoComment(@PathVariable("videoId") Long videoId, @RequestParam("articleId") Long articleId) {
        cmsCommentService.delete(articleId);

        return null;
    }


    @GetMapping
    public ResultBean<CmsCommentVO> search(@RequestBody @Valid CmsCommentParam cmsCommentParam) {
        return null;
    }

    @PostMapping("/article/${articleId}/comment")
    public ResultBean<Integer> sendArticleComment(@RequestBody @Validated(ValidationGroup.addGroup.class) CmsCommentParam cmsCommentParam) {
        cmsCommentService.sendComment(cmsCommentParam, ContentType.ARTICLE);
        return null;
    }

    @PostMapping("/video/${videoId}/comment")
    public ResultBean<Integer> sendVideoComment(@RequestBody @Validated(ValidationGroup.addGroup.class) CmsCommentParam cmsCommentParam) {
        cmsCommentService.sendComment(cmsCommentParam, ContentType.VIDEO);
        return null;
    }


    @PostMapping("/comment/fold")
    public ResultBean<Boolean> fold(Long id) {

        return null;
    }


    /**
     * 获取首页评论类型,按时间排行
     */
    @GetMapping("/article/${articleId}/comment/like")
    public ResultBean<PageVO<CmsCommentVO>> pageComment(@PathVariable Integer articleId, @RequestBody @Valid PageParam pageParam) {
        PageVO<CmsCommentVO> pageVO = cmsCommentService.commentPage(pageParam, articleId, true);
        ResultBean<PageVO<CmsCommentVO>> result = new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
        result.setData(pageVO);
        return result;
    }

    /**
     * 获取首页评论类型,按热度排行
     */
    @GetMapping("/article/${articleId}/comment/time")
    public ResultBean<PageVO<CmsCommentVO>> pageCommentHeat(@PathVariable Integer articleId, @RequestBody @Valid PageParam pageParam) {
        PageVO<CmsCommentVO> pageVO = cmsCommentService.commentPage(pageParam, articleId, false);
        ResultBean<PageVO<CmsCommentVO>> result = new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
        result.setData(pageVO);
        return result;
    }


}