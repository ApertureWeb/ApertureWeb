package com.aperture.community.message.common;

import lombok.AllArgsConstructor;

/**
 * @author HALOXIAO
 * @since 2020-10-04 13:46
 **/
@AllArgsConstructor
public class MessageDto<T> {

    private String msg;

    private T data;

    private boolean flag;

    public MessageDto(String msg, boolean flag) {
        this.msg = msg;
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
