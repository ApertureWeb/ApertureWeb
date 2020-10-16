package com.aperture.community.core.manager;

import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.entity.ResultBean;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @SendTo(Source.OUTPUT)
    public ResultBean exceptionHandler(Exception e) {
        return new ResultBean("服务异常", RESULT_BEAN_STATUS_CODE.UNKNOWN_EXCEPTION);
    }
}
