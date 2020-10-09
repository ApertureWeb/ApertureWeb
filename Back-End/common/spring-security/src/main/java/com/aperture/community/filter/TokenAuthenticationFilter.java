package com.aperture.community.filter;

import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.entity.ResultBean;
import com.aperture.community.security.TokenManager;
import com.aperture.community.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Auther: JayV
 * @Date: 2020-9-23 13:24
 * @Description: 认证过滤器
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate redisTemplate) {
        super(authenticationManager);
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 内部过滤处理
     *
     * @param request
     * @param response
     * @param chain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 如果URI资源标识符中带有admin，表明是管理员登录，可以放行
        if (request.getRequestURI().indexOf("admin") == -1) {
            chain.doFilter(request, response); // 放行
            return;
        }
        UsernamePasswordAuthenticationToken authentication = null; // 存放用户的认证信息
        try {
            // 根据请求信息获取到认证信息
            authentication = getAuthentication(request);
        } catch (Exception e) {
            ResponseUtil.out(response, new ResultBean("认证失败", RESULT_BEAN_STATUS_CODE.NO_PERMISSION));
        }

        if (authentication != null) {
            // 往security容器中添加认证信息，该容器是全局容器，其他类方法可以获得里面的信息
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            ResponseUtil.out(response, new ResultBean("认证失败", RESULT_BEAN_STATUS_CODE.NO_PERMISSION));
        }
        chain.doFilter(request, response);
    }

    /**
     * 根据请求信息，从redis中获取认证、权限信息
     *
     * @param request
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // 从请求头获取到token
        String token = request.getHeader("token");
        // trim(): 取出token字符串前后的空格
        if (token != null && "".equals(token.trim())) {
            String username = tokenManager.getUserFromToken(token);
            // 根据username从redis获取用户的权限信息
            List<String> permissionList = (List<String>) redisTemplate.opsForValue().get(username);
            // 用户权限信息
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            for (String permission : permissionList) {
                if (StringUtils.isEmpty(permission)) {
                    continue;
                }
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
                authorities.add(authority);  // 添加认证信息
            }

            if (!StringUtils.isEmpty(username)) {
                // 将用户的认证信息返回
                return new UsernamePasswordAuthenticationToken(username, token, authorities);
            }
            return null;
        }
        return null;
    }
}