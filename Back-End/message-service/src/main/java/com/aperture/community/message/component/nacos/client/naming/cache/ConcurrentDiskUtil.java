package com.aperture.community.message.component.nacos.client.naming.cache;

import io.vertx.core.*;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.charset.spi.CharsetProvider;

/**
 * @author HALOXIAO
 * @since 2020-10-28 18:16
 **/
public class ConcurrentDiskUtil {

    private static final int RETRY_COUNT = 5;
    private static final int SLEEP_BASETIME = 10;


    private static final Logger logger = LoggerFactory.getLogger(ConcurrentDiskUtil.class);

    //做一个优化
    public static Boolean writeFileContent(FileSystem fileSystem, WorkerExecutor executor, String filePath, String content, String charsetName) throws IOException {
        if (fileSystem.exists(filePath).failed() && fileSystem.createFile(filePath).failed()) {
            return false;
        }
        executor.executeBlocking(res -> {
            FileLock lock = null;
            try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
                 FileChannel channel = raf.getChannel()) {
                int i = 0;
                do {
                    try {
                        lock = channel.tryLock();
                    } catch (Exception e) {
                        ++i;
                        if (i > RETRY_COUNT) {
                            logger.error("[NA] write {} fail;retryed time:{}", filePath, i);
                            throw new IOException("write " + filePath + " conflict", e);
                        }
                        sleep(SLEEP_BASETIME * i);
                        logger.warn("write " + filePath + " conflict;retry time: " + i);
                    }
                } while (null == lock);
                ByteBuffer sendBuffer = ByteBuffer.wrap(content.getBytes(charsetName));
                while (sendBuffer.hasRemaining()) {
                    channel.write(sendBuffer);
                }
                channel.truncate(content.length());
            } catch (IOException e) {
                logger.error("file write error", e);
            } finally {
                if (lock != null) {
                    try {
                        lock.release();
                    } catch (IOException e) {
                        logger.warn("lock close wrong", e);
                    }
                }
            }
        });
        return true;
    }

    public static void getFileContent(WorkerExecutor executor, Vertx vertx, String address, String filepath, String charsetName) {
        executor.executeBlocking(exe -> {
            FileLock rlock = null;
            try (RandomAccessFile fis = new RandomAccessFile(filepath, "r");
                 FileChannel fcin = fis.getChannel()
            ) {
                int i = 0;
                do {
                    try {
                        rlock = fcin.tryLock(0, Long.MAX_VALUE, true);
                    } catch (Exception e) {
                        ++i;
                        if (i > RETRY_COUNT) {
                            logger.error("[NA] read " + logger.getName() + " fail;retryed time: " + i, e);
                            throw new IOException("read " + filepath + " conflict");
                        }
                        sleep(SLEEP_BASETIME * i);
                        logger.warn("read " + logger.getName() + " conflict;retry time: " + i);
                    }
                } while (rlock == null);
                int fileSize = (int) fcin.size();
                ByteBuffer byteBuffer = ByteBuffer.allocate(fileSize);
                fcin.read(byteBuffer);
                byteBuffer.flip();
                vertx.eventBus().send(address, byteBuffer.toString());
            } catch (IOException e) {
                logger.error("fail to read cache", e);
            } finally {
                if (rlock != null) {
                    try {
                        rlock.release();
                    } catch (IOException e) {
                        logger.warn("release file lock fail", e);
                    }
                }
            }
        });
    }

    private static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            logger.warn("sleep wrong", e);
        }
    }

    public static String byteBufferToString(ByteBuffer buffer, String charsetName) throws IOException {
        Charset charset = null;
        CharsetDecoder decoder = null;
        CharBuffer charBuffer = null;
        charset = Charset.forName(charsetName);
        decoder = charset.newDecoder();
        charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
        return charBuffer.toString();
    }

    static void exceptionWrapper(FileLock lock) throws Exception {
        if (lock != null) {
            lock.release();
        }
    }


}




