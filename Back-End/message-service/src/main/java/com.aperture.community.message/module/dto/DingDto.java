package com.aperture.community.message.module.dto;

/**
 * @author HALOXIAO
 * @since 2020-10-15 21:06
 **/
public class DingDto {

    private String message;
    private DingAt at;

}

class DingAt {
    String[] atMobiles;
    boolean isAtAll = false;
}