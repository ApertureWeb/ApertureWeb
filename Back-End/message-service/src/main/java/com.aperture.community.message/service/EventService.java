package com.aperture.community.message.service;


import com.aperture.community.message.manager.EventManager;
import io.vertx.mysqlclient.MySQLPool;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventManager eventManager;
    private final MySQLPool pool;

    public EventService(EventManager eventManager, MySQLPool pool) {
        this.eventManager = eventManager;
        this.pool = pool;
    }



}
