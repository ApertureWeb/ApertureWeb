package com.aperture.community.core.controller;

import com.aperture.community.core.module.param.CmsReplyParam;
import com.aperture.community.core.module.validation.ValidationGroup;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.CmsReplyVO;
import com.aperture.community.core.service.CmsCommentService;
import com.aperture.community.entity.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author HALOXIAO
 * @since 2020-10-04 16:40
 **/
@RestController
public class CmsReplyController {

    @Autowired
    private CmsCommentService service;


    @PostMapping("/comment/reply")
    public ResultBean<Boolean> sendReply(@RequestBody @Validated(ValidationGroup.addGroup.class) CmsReplyParam cmsReplyParam) {
//        umsCommentService.sendR
        return null;
    }

    @GetMapping()
    public ResultBean<PageVO<CmsReplyVO>> getReply(@RequestParam Integer page, @RequestParam Integer size, @RequestParam Long commentId) {

        return null;
    }


}
