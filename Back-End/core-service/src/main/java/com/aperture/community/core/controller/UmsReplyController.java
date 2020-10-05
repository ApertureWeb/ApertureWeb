package com.aperture.community.core.controller;

import com.aperture.community.core.module.param.UmsReplyParam;
import com.aperture.community.core.module.validation.ValidationGroup;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.UmsReplyVO;
import com.aperture.community.core.service.UmsCommentService;
import com.aperture.community.entity.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author HALOXIAO
 * @since 2020-10-04 16:40
 **/
@RestController
public class UmsReplyController {

    @Autowired
    private UmsCommentService service;


    @PostMapping("/comment/reply")
    public ResultBean<Boolean> sendReply(@RequestBody @Validated(ValidationGroup.addGroup.class) UmsReplyParam umsReplyParam) {
//        umsCommentService.sendR
        return null;
    }

    @GetMapping()
    public ResultBean<PageVO<UmsReplyVO>> getReply(@RequestParam Integer page, @RequestParam Integer size, @RequestParam Long commentId) {

        return null;
    }


}
