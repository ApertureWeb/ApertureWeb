package com.aperture.community.filter;

import com.aperture.community.entity.SecurityUser;
import com.aperture.community.entity.User;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.entity.ResultBean;
import com.aperture.community.security.TokenManager;
import com.aperture.community.utils.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: JayV
 * @Date: 2020-9-23 15:18
 * @Description: 登录过滤器，对登录的用户名和密码进行登录校验
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    // 用户登录过滤
    public TokenLoginFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.setPostOnly(false);
        // 设置请求认证的路径匹配和请求方式
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/acl/login", "POST"));
    }

    /**
     * 从请求输入流中获取到user，从而获取到username和password
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 从请求输入流中读取到json并转换为User对象并返回
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            // 根据username和password获取并返回认证信息
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 认证、登录成功
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 获取user权限对象
        SecurityUser securityUser = (SecurityUser) authResult.getPrincipal();
        String token = tokenManager.createToken(securityUser.getCurrentUserInfo().getUsername());
        // 将用户名作为key，权限信息作为value保存到redis中
        redisTemplate.opsForValue().set(securityUser.getCurrentUserInfo().getUsername(), securityUser.getPermisionValueList());
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        // 返回认证成功的信息和token值给前端
        ResponseUtil.out(response, new ResultBean<Map<String, Object>>("认证成功", RESULT_BEAN_STATUS_CODE.SUCCESS, tokenMap));
    }

    /**
     * 认证、登录失败
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response, new ResultBean("认证失败", RESULT_BEAN_STATUS_CODE.NO_PERMISSION));
    }
}