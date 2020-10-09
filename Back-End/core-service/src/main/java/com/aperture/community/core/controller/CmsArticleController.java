package com.aperture.community.core.controller;


import com.aperture.community.core.module.vo.CmsArticleViewVO;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.core.module.param.CmsArticleParam;
import com.aperture.community.core.module.validation.ValidationGroup;
import com.aperture.community.core.service.impl.CmsArticleServiceImpl;
import com.aperture.community.entity.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-09-23
 */
@RestController("/asdas/")
public class CmsArticleController {


//    TODO 压缩

    @Autowired
    CmsArticleServiceImpl umsArticleService;

    @PostMapping
    public ResultBean<Integer> saveArticle(@RequestBody @Validated({ValidationGroup.addGroup.class}) CmsArticleParam cmsArticleParam) {
        ResultBean<Integer> bean = new ResultBean<>("success", RESULT_BEAN_STATUS_CODE.SUCCESS);
        return bean;
    }

    @DeleteMapping
    public ResultBean<Boolean> deleteArticle(Long id) {
        return null;
    }

    @PostMapping
    public ResultBean<String> uploadPicture() {
        return null;
    }


    @GetMapping
    public ResultBean<CmsArticleViewVO> getArticle(@RequestParam("articleId") Long id) {

        return null;
    }

}

