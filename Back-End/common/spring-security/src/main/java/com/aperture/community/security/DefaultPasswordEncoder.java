package com.aperture.community.security;

import com.aperture.community.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Auther: JayV
 * @Date: 2020-9-22 21:44
 * @Description:
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder() {
        this(-1);
    }

    public DefaultPasswordEncoder(int strength) {

    }

    /**
     * 使用MD5对用户密码加密
     * @param rawPassword
     * @return
     */
    public String encode(CharSequence rawPassword) {
        return MD5.encrypt(rawPassword.toString());
    }

    /**
     * 密码加密后和数据库的密码进行匹配
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
    }

}