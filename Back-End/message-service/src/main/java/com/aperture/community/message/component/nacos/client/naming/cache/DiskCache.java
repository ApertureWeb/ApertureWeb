package com.aperture.community.message.component.nacos.client.naming.cache;

import com.aperture.community.message.component.nacos.api.common.Constants;
import com.aperture.community.message.component.nacos.api.naming.pojo.Instance;
import com.aperture.community.message.component.nacos.api.naming.pojo.ServiceInfo;
import com.aperture.community.message.component.nacos.api.utils.StringUtils;
import com.aperture.community.message.component.nacos.client.naming.utils.CollectionUtils;
import com.aperture.community.message.component.nacos.common.utils.BufferUtils;
import com.aperture.community.message.component.nacos.common.utils.FileUtils;
import com.aperture.community.message.component.nacos.common.utils.JacksonUtils;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileProps;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

import java.io.*;
import java.lang.reflect.InaccessibleObjectException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemException;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static Map<String, ServiceInfo> read(Vertx vertx, WorkerExecutor executor, String cacheDir) {
        Map<String, ServiceInfo> domMap = new HashMap<String, ServiceInfo>(16);

        FileSystem fileSystem = vertx.fileSystem();
        makeSureCacheDirExists(vertx.fileSystem(), cacheDir);
        List<String> files = fileSystem.readDir(cacheDir).onFailure(er -> {
            logger.error("can not read dir ");
        }).result();
        if (files == null || files.size() == 0) {
            return domMap;
        }
        OpenOptions openOptions = new OpenOptions();
        openOptions.setRead(true);
        openOptions.setWrite(false);
        for (String filePath : files) {
            fileSystem.lprops(filePath).onSuccess(file -> {
                        if (!file.isRegularFile()) {//replace continue
                        } else {
                            String fileName = URLDecoder.decode(filePath, StandardCharsets.UTF_8);
                            if (fileName.endsWith(Constants.SERVICE_INFO_SPLITER + "meta") || fileName
                                    .endsWith(Constants.SERVICE_INFO_SPLITER + "special-url")) {
                                ServiceInfo dom = new ServiceInfo(fileName);
                                List<Instance> ips = new ArrayList<>();
                                dom.setHosts(ips);
                                ServiceInfo newFormat = null;

                                try {
                                    String dataString = ConcurrentDiskUtil
                                            .getFileContent(executor, cacheDir, Charset.defaultCharset().toString());
                                    if (dataString == null) {
                                        throw new IOException("fail to read file content" + cacheDir);
                                    }

                                    Buffer buffer = Buffer.buffer(dataString);
                                    String line;
                                    // format
                                    String json;
                                    while ((json = BufferUtils.nonLockReadLine(buffer)) != null) {
                                        try {
                                            if (!json.startsWith("{")) {
                                                continue;
                                            }
                                            newFormat = JacksonUtils.toObj(json, ServiceInfo.class);
                                            if (StringUtils.isEmpty(newFormat.getName())) {
                                                ips.add(JacksonUtils.toObj(json, Instance.class));
                                            }
                                        } catch (Throwable e) {
                                            logger.error("[NA] error while parsing cache file: " + json, e);
                                        }
                                    }
                                    //he
                                } catch (Exception e) {
                                    logger.error("[NA] failed to read cache for dom: " + filePath, e);
                                }

                                if (newFormat != null && !StringUtils.isEmpty(newFormat.getName()) && !CollectionUtils
                                        .isEmpty(newFormat.getHosts())) {
                                    domMap.put(dom.getKey(), newFormat);
                                } else if (!CollectionUtils.isEmpty(dom.getHosts())) {
                                    domMap.put(dom.getKey(), dom);
                                }
                            }
                        }
                    }
            ).onFailure(
                    err -> {
                        logger.error("[NA] failed to read cache file", err.getCause());
                    }
            );
            return domMap;


        }


        return domMap;
    }


    private static void makeSureCacheDirExists(FileSystem fileSystem, String dir) {
        if (fileSystem.exists(dir).failed()) {
            if (fileSystem.mkdir(dir).failed() && fileSystem.exists(dir).failed()) {
                throw new IllegalStateException("fail to create cache dir:" + dir);
            }
        }


    }

}
