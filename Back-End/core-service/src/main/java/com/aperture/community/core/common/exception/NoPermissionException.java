package com.aperture.community.core.common.exception;

/**
 * @author HALOXIAO
 * @date 2020/11/16
 */
public class NoPermissionException extends RuntimeException {
    public NoPermissionException(String msg) {
        super(msg);
    }

    public NoPermissionException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

}
