package com.aperture.community.message.service;


import com.aperture.community.message.config.MySQLConnectionFactory;
import com.aperture.community.message.manager.EventManager;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventManager eventManager;
    private final MySQLConnectionFactory mySQLConnectionFactory;

    public EventService(EventManager eventManager, MySQLConnectionFactory factory) {
        this.eventManager = eventManager;
        this.mySQLConnectionFactory = factory;
    }


    public boolean saveEvent() {
        return false;
    }


}
