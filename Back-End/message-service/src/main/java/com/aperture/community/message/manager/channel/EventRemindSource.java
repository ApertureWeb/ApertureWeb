package com.aperture.community.message.manager.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author HALOXIAO
 * @since 2020-10-11 20:05
 **/
public interface EventRemindSource {
    String EVENT_REMIND_CHANNEL = "event_remind";

    @Input(EVENT_REMIND_CHANNEL)
    SubscribableChannel subscribableEventRemindChannel();

}
