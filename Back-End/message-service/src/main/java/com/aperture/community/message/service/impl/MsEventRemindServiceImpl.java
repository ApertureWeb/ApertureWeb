package com.aperture.community.message.service.impl;

import com.aperture.community.message.dao.MsEventRemindMapper;
import com.aperture.community.message.manager.channel.EventRemindChannel;
import com.aperture.community.message.module.converter.EventRemindConverter;
import com.aperture.community.message.module.dto.EventRemindDto;
import com.aperture.community.message.service.MsEventRemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(EventRemindChannel.class)
public class MsEventRemindServiceImpl implements MsEventRemindService {

    private MsEventRemindMapper eventRemindMapper;

    @Autowired
    public MsEventRemindServiceImpl(MsEventRemindMapper msEventRemindMapper) {
        this.eventRemindMapper = msEventRemindMapper;
    }


    /**
     * 接收机制
     */
    @StreamListener(target = EventRemindChannel.EVENT_REMIND_CHANNEL)
    public void add(@Payload EventRemindDto dto) {
        eventRemindMapper.save(EventRemindConverter.INSTANCE.toEventRemindEntity(dto));
    }

    @SendTo(EventRemindChannel.EVENT_REMIND_RELAY_CHANNEL)
    public EventRemindDto asd() {
        return null;
    }

}