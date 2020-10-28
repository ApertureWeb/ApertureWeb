package com.aperture.community.message.component.nacos.common.utils;

import io.vertx.core.file.FileSystem;

/**
 * @author HALOXIAO
 * @since 2020-10-28 17:08
 **/
public class FileUtils {

    public static String getPathString(String parent, String child) {
        String result;
        if (child == null) {
            throw new NullPointerException();
        }
        //absolute path
        if (child.indexOf("/") == 0) {
            return child;
        }
        if (parent != null) {
            if (parent.lastIndexOf("/") != (parent.length() - 1)) {
                result = parent + "/" + child;
            } else {
                result = parent + child;
            }
        } else {
            result = System.getProperty("user.dir") + "/" + child;
        }
        return result;
    }

}
