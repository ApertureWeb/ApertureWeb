package com.aperture.community.message.component.nacos.common.utils;

import io.vertx.core.buffer.Buffer;
import lombok.val;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class BufferReader {

    private final Buffer buffer;
    private int position = 0;
    private byte[] bytes;

    public BufferReader(Buffer buffer) {
        this.buffer = buffer;
        bytes = buffer.getBytes();
    }


    /**
     * Do not use it in a concurrent env and if you want to get the data fluently,do not change the read position;
     *
     * @param buffer io.vertx.core.buffer.Buffer
     * @return data in line
     */
    public String nonLockReadLine() {
        Objects.requireNonNull(buffer);
        StringBuilder builder = new StringBuilder();
        return null;
    }



}
