package com.aperture.community.message.component.nacos.client.naming.cache;

import com.aperture.community.message.component.nacos.api.naming.pojo.ServiceInfo;
import com.aperture.community.message.component.nacos.common.utils.FileUtils;
import com.aperture.community.message.component.nacos.common.utils.JacksonUtils;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.file.FileSystem;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

import java.nio.charset.Charset;

/**
 * @author HALOXIAO
 * @since 2020-10-28 16:16
 **/
public class DiskCache {

    private static Logger logger = LoggerFactory.getLogger(DiskCache.class);

    public static void write(ServiceInfo dom, String dir, Vertx vertx, WorkerExecutor executor) {
        FileSystem fileSystem = vertx.fileSystem();
        try {
            makeSureCacheDirExists(fileSystem, dir);
            String path = FileUtils.getPathString(dir, dom.getKeyEncoded());
            if (fileSystem.exists(path).failed()) {
                if (fileSystem.createFile(path).failed() && fileSystem.exists(path).failed()) {
                    throw new IllegalStateException("failed to create cache file");
                }
            }
            StringBuilder keyContentBuffer = new StringBuilder();

            String json = dom.getJsonFromServer();

            if (json.isEmpty()) {
                json = JacksonUtils.toJson(dom);
            }
            keyContentBuffer.append(json);
            ConcurrentDiskUtil.writeFileContent(fileSystem, executor, dir, keyContentBuffer.toString(), Charset.defaultCharset().toString());

        } catch (Throwable e) {
            logger.error("[NA] failed to write cache for dom:" + dom.getName(), e);
        }
    }



    public static void makeSureCacheDirExists(FileSystem fileSystem, String dir) {
        if (fileSystem.exists(dir).failed()) {
            if (fileSystem.mkdir(dir).failed() && fileSystem.exists(dir).failed()) {
                throw new IllegalStateException("fail to create cache dir:" + dir);
            }
        }


    }


}
