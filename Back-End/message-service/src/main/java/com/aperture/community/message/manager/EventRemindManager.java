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
@EnableBinding(EventRemindChannel.class)
public class EventRemindManager {

    private MsEventRemindMapper eventRemindMapper;

    @Autowired
    EventRemindManager() {

    }

    @StreamListener(target = EventRemindChannel.EVENT_REMIND_CHANNEL)
    public void add(@Payload EventRemindDto dto) {
        eventRemindMapper.save(EventRemindConverter.INSTANCE.toEventRemindEntity(dto));
    }

    @SendTo(EventRemindChannel.EVENT_REMIND_RELAY_CHANNEL)
    public EventRemindDto asd() {
        return null;
    }


}
