package com.aperture.community.message.dao;

import io.vertx.core.Vertx;
import io.vertx.mysqlclient.MySQLPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author HALOXIAO
 * @since 2020-10-19 17:00
 **/
@Repository
public class EventMapper {

    private final MySQLPool pool;
    private final Vertx vertx;

    @Autowired
    public EventMapper(MySQLPool pool, Vertx vertx) {
        this.pool = pool;
        this.vertx = vertx;
    }

    public void save() {
        pool.getConnection();
    }


    private String asd() {

        return null;
    }

}
