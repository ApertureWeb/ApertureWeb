package com.aperture.community.config;

import com.aperture.community.filter.TokenAuthenticationFilter;
import com.aperture.community.filter.TokenLoginFilter;
import com.aperture.community.security.DefaultPasswordEncoder;
import com.aperture.community.security.TokenLogoutHandler;
import com.aperture.community.security.TokenManager;
import com.aperture.community.security.UnAuthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;


/**
 * @Auther: JayV
 * @Date: 2020-9-22 20:47
 * @Description: Security核心配置类
 */
@Configuration
@EnableWebSecurity // 激活WebSecurityConfiguration核心配置类
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启全局Security权限认证
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;    // 原生的User处理类
    private TokenManager tokenManager;     // token操作类
    private DefaultPasswordEncoder defaultPasswordEncoder; // 密码处理类
    private RedisTemplate redisTemplate;

    @Autowired
    public TokenWebSecurityConfig(UserDetailsService userDetailsService, DefaultPasswordEncoder defaultPasswordEncoder,
                                  TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 配置HTTP请求的异常设置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new UnAuthorizedEntryPoint())
                .and().csrf().disable()  /*禁止请求跨域攻击*/
                .authorizeRequests()
                .anyRequest().authenticated()  /*任何请求都需要认证*/
                .and().logout().logoutUrl("/admin/acl/index/logout")/*设置登出路径*/
                .addLogoutHandler(new TokenLogoutHandler(tokenManager, redisTemplate)).and()/*登出处理类*/
                .addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate))/*登录过滤器*/
                .addFilter(new TokenAuthenticationFilter(authenticationManager(), tokenManager, redisTemplate))/*认证过滤器*/
                .httpBasic();
    }

    /**
     * 设置忽略不拦截的请求路径
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**", "/webjars/**", "swagger-ui.html/**", "/v2/**", "/swagger-resources/**");
    }

    /**
     * 密码处理
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }
}