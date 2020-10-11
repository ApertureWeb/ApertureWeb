package com.aperture.community.message.module.dto;

import com.aperture.community.message.manager.channel.EventRemindChannel;
import lombok.Data;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author HALOXIAO
 * @since 2020-10-11 19:49
 **/
@Data
@EnableBinding(EventRemindChannel.class)
public class EventRemindDto {



}
