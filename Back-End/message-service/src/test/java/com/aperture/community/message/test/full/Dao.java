package com.aperture.community.message.test.full;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

import java.util.Optional;

public class Dao {


    private MySQLPool pool;
    private final String query = "SELECT * FROM test";

    public Dao(MySQLPool pool) {
        this.pool = pool;
    }

    public Future<Optional<Object>> queryObject() {
        pool.query(query).execute();

        return null;
    }


}
