package com.aperture.community.message.manager;

import com.aperture.community.message.dao.MsEventRemindMapper;
import com.aperture.community.message.manager.channel.EventRemindChannel;
import com.aperture.community.message.module.converter.EventRemindConverter;
import com.aperture.community.message.module.dto.EventRemindDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * @author HALOXIAO
 * @since 2020-10-11 18:59
 **/
public class EventRemindManager {

    private MsEventRemindMapper eventRemindMapper;

    @Autowired
    EventRemindManager() {

    }




}
