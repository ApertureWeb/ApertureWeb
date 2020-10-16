package com.aperture.community.message.module.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author HALOXIAO
 * @since 2020-10-15 21:06
 **/
public class DingDto {

    private String message;

    private DingAt at;


    @JsonCreator
    public DingDto(@JsonProperty("msg") String message, DingAt at) {
        this.message = message;
        this.at = at;
    }


}

class DingAt {
    String[] atMobiles;
    boolean isAtAll = false;
}