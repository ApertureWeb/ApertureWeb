package com.aperture.community.message.component.nacos.client.naming.cache;

import io.vertx.core.Vertx;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;

import java.io.File;
import java.io.IOException;

/**
 * @author HALOXIAO
 * @since 2020-10-28 18:16
 **/
public class ConcurrentDiskUtil {

    public static Boolean writeFileContent(FileSystem fileSystem, String filePath, String content, String charsetName) throws IOException {
        if (fileSystem.exists(filePath).failed() && fileSystem.createFile(filePath).failed()) {
            return false;
        }
        Vertx vertx = Vertx.vertx();
        OpenOptions openOptions = new OpenOptions();

        return false;
    }
}
