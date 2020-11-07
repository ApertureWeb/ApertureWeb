package com.aperture.community.core.controller;


import com.aperture.community.core.module.param.CmsArticleParam;
import com.aperture.community.core.module.validation.ValidationGroup;
import com.aperture.community.core.module.vo.CmsArticleViewVO;
import com.aperture.community.core.service.impl.CmsArticleServiceImpl;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.entity.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-09-23
 */
@RestController("/api/v1/article")
public class CmsArticleController {


    @Autowired
    CmsArticleServiceImpl umsArticleService;


    @PostMapping
    public ResultBean saveArticle(@RequestBody @Validated({ValidationGroup.addGroup.class}) CmsArticleParam cmsArticleParam) {
        umsArticleService.save(cmsArticleParam);
        return ResultBean.ok();
    }

    @DeleteMapping
    public ResultBean<Boolean> deleteArticle(Long id) {
        return umsArticleService.delete(id) ? ResultBean.ok() : ResultBean.error("删除失败", RESULT_BEAN_STATUS_CODE.UNKNOWN_EXCEPTION);
    }


    @GetMapping
    public ResultBean<CmsArticleViewVO> getArticle(@RequestParam("articleId") Long id) {
        umsArticleService.select(id);

        return null;
    }

    private boolean checkSize(MultipartFile file) {
        return false;
    }


}

