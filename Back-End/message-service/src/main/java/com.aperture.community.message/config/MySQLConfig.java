package com.aperture.community.message.config;

import io.vertx.core.Vertx;
import io.vertx.mysqlclient.MySQLPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author HALOXIAO
 * @since 2020-10-16 21:37
 **/
@Configuration
@Import((VertxConfig.class))
public class MySQLConfig {

    public final Vertx vertx;

    @Autowired
    public MySQLConfig(Vertx vertx) {
        this.vertx = vertx;
    }

    @Bean
    public MySQLPool getConnection() {
//        MySQLPool.pool();
        return null;
    }


}
