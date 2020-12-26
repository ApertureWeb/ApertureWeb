package com.aperture.community.auth.service;

import com.aperture.community.auth.vo.UserRegistVo;
import org.springframework.validation.BindingResult;

/**
 * @Auther: JayV
 * @Date: 2020-11-14 14:44
 * @Description:
 */
public interface RegistService {
    void regist(UserRegistVo userRegistVo, BindingResult result);
}