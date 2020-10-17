package com.aperture.community.member.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Auther: JayV
 * @Date: 2020-10-11 13:44
 * @Description:
 */
@Configuration
@EnableTransactionManagement
@EnableScheduling
@MapperScan("com.aperture.community.member.dao")
public class MybatisConfig {

    /**
     * mybatis-plus分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作，true返回首页，false则继续请求，默认为false
        paginationInterceptor.setOverflow(true);
        //每页最大限制数量，默认500，-1不受限制
        paginationInterceptor.setLimit(1000);
        return paginationInterceptor;
    }

}