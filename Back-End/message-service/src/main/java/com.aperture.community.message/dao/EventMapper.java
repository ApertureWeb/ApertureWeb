package com.aperture.community.message.dao;

import com.aperture.community.message.module.dto.EventBusDto;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.spi.VerticleFactory;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.SqlConnection;
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
        Future<SqlConnection> connectionFuture = pool.getConnection();
        EventBus bus = vertx.eventBus();
        if (!connectionFuture.succeeded()) {

        } else {
            SqlConnection conn = connectionFuture.result();
            //conn.prepare("INSERT INTO ms_event_remind(event_remind_id,action,source_id)")

        }
    }


    private String asd() {

        return null;
    }

}
