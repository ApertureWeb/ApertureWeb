package com.aperture.community.message.config;

import io.vertx.core.Future;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.SqlConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MySQLConfig.class)
public class MySQLConnectionFactory {

    private MySQLPool mySQLPool;

    @Autowired
    public MySQLConnectionFactory(MySQLPool mySQLPool) {
        this.mySQLPool = mySQLPool;
    }

    private MySQLConnectionFactory() {
    }

    public Future<SqlConnection> getConnectionInstance() {
        return mySQLPool.getConnection();
    }

}
