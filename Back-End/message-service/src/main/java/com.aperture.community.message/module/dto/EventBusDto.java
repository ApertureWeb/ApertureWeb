package com.aperture.community.message.module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author HALOXIAO
 * @since 2020-10-20 12:48
 **/
@Data
@AllArgsConstructor
public class EventBusDto<T> {
    private boolean success;
    private String msg;
    private T data;

    public EventBusDto(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}
