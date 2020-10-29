package com.aperture.community.message.component.nacos.client.naming.cache;

import com.aperture.community.message.component.nacos.api.common.Constants;
import com.aperture.community.message.component.nacos.api.naming.pojo.Instance;
import com.aperture.community.message.component.nacos.api.naming.pojo.ServiceInfo;
import com.aperture.community.message.component.nacos.api.utils.StringUtils;
import com.aperture.community.message.component.nacos.client.naming.utils.CollectionUtils;
import com.aperture.community.message.component.nacos.common.utils.FileUtils;
import com.aperture.community.message.component.nacos.common.utils.JacksonUtils;
import io.vertx.core.Vertx;
import io.vertx.core.file.FileProps;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
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

    public static void write(ServiceInfo dom, String dir, Vertx vertx) {
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
            ConcurrentDiskUtil.writeFileContent(vertx, dir, keyContentBuffer.toString(), Charset.defaultCharset().toString());

        } catch (Throwable e) {
            logger.error("[NA] failed to write cache for dom:" + dom.getName(), e);
        }
    }

    public static Map<String, ServiceInfo> read(Vertx vertx, String cacheDir) {
        Map<String, ServiceInfo> domMap = new HashMap<String, ServiceInfo>(16);

        FileSystem fileSystem = vertx.fileSystem();
        BufferedReader reader = null;
        makeSureCacheDirExists(vertx.fileSystem(), cacheDir);
        List<String> files = fileSystem.readDir(cacheDir).onFailure(er -> {
            logger.error("can not read dir ");
        }).result();
        if (files == null || files.size() == 0) {
            return domMap;
        }
        for (String filePath : files) {
            fileSystem.lprops(filePath).onSuccess(
                    file -> {
                        try {
                            if (file.isRegularFile()) {
                                String fileName = URLDecoder.decode(filePath, StandardCharsets.UTF_8);
                                if (fileName.endsWith(Constants.SERVICE_INFO_SPLITER + "meta") || fileName
                                        .endsWith(Constants.SERVICE_INFO_SPLITER + "special-url")) {
                                    ServiceInfo dom = new ServiceInfo(fileName);
                                    List<Instance> ips = new ArrayList<>();
                                    dom.setHosts(ips);
                                    ServiceInfo newFormat = null;

                                }

                            }
                        } catch (Throwable e) {

                        }
                    }
            ).onFailure(
                    err -> {
                        logger.error("[NA] failed to read cache file", err.getCause());
                    }
            );
            if (!file.isFile()) {
                continue;
            }

            String fileName = URLDecoder.decode(file.getName(), "UTF-8");

            if (!(fileName.endsWith(Constants.SERVICE_INFO_SPLITER + "meta") || fileName
                    .endsWith(Constants.SERVICE_INFO_SPLITER + "special-url"))) {
                ServiceInfo dom = new ServiceInfo(fileName);
                List<Instance> ips = new ArrayList<Instance>();
                dom.setHosts(ips);

                ServiceInfo newFormat = null;

                try {
                    String dataString = ConcurrentDiskUtil
                            .getFileContent(file, Charset.defaultCharset().toString());
                    reader = new BufferedReader(new StringReader(dataString));

                    String json;
                    while ((json = reader.readLine()) != null) {
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
                } catch (Exception e) {
                    logger.error("[NA] failed to read cache for dom: " + file.getName(), e);
                } finally {
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (Exception e) {
                        //ignore
                    }
                }
                if (newFormat != null && !StringUtils.isEmpty(newFormat.getName()) && !CollectionUtils
                        .isEmpty(newFormat.getHosts())) {
                    domMap.put(dom.getKey(), newFormat);
                } else if (!CollectionUtils.isEmpty(dom.getHosts())) {
                    domMap.put(dom.getKey(), dom);
                }
            }

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
