package com.aperture.community.security;

import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.entity.ResultBean;
import com.aperture.community.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: JayV
 * @Date: 2020-9-23 13:17
 * @Description: 退出登录业务逻辑处理类
 */
public class TokenLogoutHandler implements LogoutHandler {

    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenLogoutHandler(TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @param authentication
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = request.getHeader("token");  // 从请求头中获取到token
        if(token != null) {
            String userName = tokenManager.getUserFromToken(token);
            redisTemplate.delete(userName);  // 根据用户名删除redis中保存的对应数据
        }
        ResponseUtil.out(response, new ResultBean("退出登录", RESULT_BEAN_STATUS_CODE.SUCCESS));

    }
}