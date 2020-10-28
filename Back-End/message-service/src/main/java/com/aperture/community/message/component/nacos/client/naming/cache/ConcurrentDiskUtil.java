package com.aperture.community.message.component.nacos.client.naming.cache;

import io.vertx.core.*;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @author HALOXIAO
 * @since 2020-10-28 18:16
 **/
public class ConcurrentDiskUtil {

    private static Logger logger = LoggerFactory.getLogger(ConcurrentDiskUtil.class);

    public static Boolean writeFileContent(Vertx vertx, String filePath, String content, String charsetName) throws IOException {
        FileSystem fileSystem = vertx.fileSystem();
        if (fileSystem.exists(filePath).failed() && fileSystem.createFile(filePath).failed()) {
            return false;
        }
        WorkerExecutor executor = vertx.createSharedWorkerExecutor("concurrentFile");
        executor.executeBlocking(res -> {
            try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
                 FileChannel channel = raf.getChannel()
            ) {
                FileLock lock = null;
                int i = 0;
                do {
                    lock = channel.tryLock();

                } while (lock == null);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }).succeeded();
        return null;
    }


}




