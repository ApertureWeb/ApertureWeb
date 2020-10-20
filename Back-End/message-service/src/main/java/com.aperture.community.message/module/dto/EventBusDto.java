package com.aperture.community.message.module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author HALOXIAO
 * @since 2020-10-20 12:48
 **/
@Data
@AllArgsConstructor
public class EventBusDto {
    private boolean success;
    private String msg;
}
