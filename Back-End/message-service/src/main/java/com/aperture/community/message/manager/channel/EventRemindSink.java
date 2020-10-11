package com.aperture.community.message.manager.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author HALOXIAO
 * @since 2020-10-11 20:20
 **/
public interface EventRemindSink {
     String EVENT_REMIND_RELAY_CHANNEL = "event_remind_relay";

    @Output(EVENT_REMIND_RELAY_CHANNEL)
    MessageChannel eventRemindChannel();

}
