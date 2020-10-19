package com.aperture.community.message.service;


import com.aperture.community.message.manager.EventManager;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventManager eventManager;

    public EventService(EventManager eventManager) {
        this.eventManager = eventManager;
    }




}
