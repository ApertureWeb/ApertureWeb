package com.aperture.community.auth.service.impl;

import com.aperture.community.auth.service.LoginService;
import com.aperture.community.auth.service.RegistService;
import com.aperture.community.auth.vo.UserRegistVo;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: JayV
 * @Date: 2020-11-14 14:45
 * @Description:
 */
@Service
public class RegistSercviceImpl implements RegistService {

    @Override
    public void regist(UserRegistVo userRegistVo, BindingResult result) {
        // 输入校验出错
        if(result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream().collect(
                    Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));



        }
    }
}