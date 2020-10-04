package com.aperture.community.core.module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author HALOXIAO
 * @since 2020-10-04 13:46
 **/
@AllArgsConstructor
@Data
public class MessageDto<T> {


    private String msg;

    private T data;

}
