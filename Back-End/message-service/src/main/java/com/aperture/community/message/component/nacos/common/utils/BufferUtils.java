package com.aperture.community.message.component.nacos.common.utils;

import io.vertx.core.buffer.Buffer;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class BufferUtils {

    /**
     * Do not use it in a concurrent env and if you want to get the data fluently,do not change the read position;
     *
     * @param buffer io.vertx.core.buffer.Buffer
     * @return data in line
     */

    //TODO
    public static String nonLockReadLine(@NotNull Buffer buffer) {
        Objects.requireNonNull(buffer);
        int lengh = buffer.length();
        return null;

    }


}
