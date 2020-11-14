package com.aperture.community.core.filter;

import com.aperture.community.core.common.TokenResolver;
import com.aperture.community.core.module.dto.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenFilter extends HandlerInterceptorAdapter {

    private UserDto userDto;

    @Bean()
    public UserDto userDto() {
        return null;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String json = request.getHeader("token");
        TokenResolver.resolveToken(json);
        return super.preHandle(request, response, handler);
    }

}
