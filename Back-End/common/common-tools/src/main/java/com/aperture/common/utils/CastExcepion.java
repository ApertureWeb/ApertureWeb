package com.aperture.common.utils;

import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: JayV
 * @Date: 2020-10-14 13:56
 * @Description:
 */
@Slf4j
public class CastExcepion {

    public static void cast(RESULT_BEAN_STATUS_CODE code) {
        log.error(code.toString());
        throw new RRException(code.getValue());
    }

    public static void cast(String msg, RESULT_BEAN_STATUS_CODE code) {
        log.error(msg);
        throw new RRException(msg, code.getValue());
    }

}