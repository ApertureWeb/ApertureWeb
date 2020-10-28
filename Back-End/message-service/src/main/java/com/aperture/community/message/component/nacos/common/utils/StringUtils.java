package com.aperture.community.message.component.nacos.common.utils;

import com.aperture.community.message.component.nacos.api.common.Constants;

import java.nio.charset.Charset;

/**
 * @author HALOXIAO
 * @since 2020-10-28 17:26
 **/
public class StringUtils {

    public static final String DOT = ".";

    private static final int INDEX_NOT_FOUND = -1;

    public static final String COMMA = ",";

    public static final String EMPTY = "";

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String newStringForUtf8(byte[] bytes) {
        return new String(bytes, Charset.forName(Constants.ENCODE));
    }
}
