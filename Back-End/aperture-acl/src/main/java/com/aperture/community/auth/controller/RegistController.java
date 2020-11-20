package com.aperture.community.auth.controller;

import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;
import com.aperture.community.auth.service.LoginService;
import com.aperture.community.auth.service.RegistService;
import com.aperture.community.auth.vo.UserRegistVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Auther: JayV
 * @Date: 2020-11-14 12:55
 * @Description:
 */
@RestController
@RequestMapping("member/acl")
public class RegistController {

    @Autowired
    RegistService registService;

    /**
     * 注册
     */
    @PostMapping("/regist")
    public R regist(@Valid UserRegistVo userRegistVo, BindingResult result){
        registService.regist(userRegistVo, result);
        return R.ok();
    }
}