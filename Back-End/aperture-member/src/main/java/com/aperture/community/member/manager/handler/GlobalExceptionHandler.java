package com.aperture.community.member.manager.handler;

import com.aperture.common.utils.RRException;
import com.esotericsoftware.minlog.Log;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-15 19:14
 * @Description:
 */
public class GlobalExceptionHandler {

    public static void exceptionHandler(Exception e) {
        Log.info("全局异常：{}", e.getMessage());
    }

}