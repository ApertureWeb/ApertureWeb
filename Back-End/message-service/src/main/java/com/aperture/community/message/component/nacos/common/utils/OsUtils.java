package com.aperture.community.message.component.nacos.common.utils;

/**
 * @author HALOXIAO
 * @since 2020-11-05 12:02
 **/
public class OsUtils {

    private static final String WINDOWS = "windows";
    private static final String MAC = "mac";
    private static final String CONCURRENT_OS = System.getProperty("os.name");
    private static final String lineSeparator = System.getProperty("line.separator");

    public static String getLineSeparator() {
        return lineSeparator;
    }

    public static boolean isWindows() {
        return CONCURRENT_OS.toLowerCase().contains(WINDOWS);
    }

    public static boolean isMacOs() {
        return CONCURRENT_OS.toLowerCase().contains(MAC);
    }

}
