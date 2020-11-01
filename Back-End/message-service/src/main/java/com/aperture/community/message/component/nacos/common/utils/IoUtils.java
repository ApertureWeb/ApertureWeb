package com.aperture.community.message.component.nacos.common.utils;

import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.parsetools.RecordParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * @author HALOXIAO
 * @since 2020-10-31 20:31
 **/
public class IoUtils {

    public static Future<List<String>> readLines(Buffer buffer) throws IOException {
        buffer.appendString("\n");
        Future<List<String>> future = Future.succeededFuture(new ArrayList<>());
        RecordParser parser = RecordParser.newDelimited("\n", line -> {
            if (line != null && !"\n".equals(line.toString())) {
                if (StringUtils.isNotEmpty(line.toString())) {
                    future.result().add(line.toString());
                }
            }
        });
        parser.handle(buffer);
        return future;
    }

    public static byte[] tryDecompress(byte[] raw) throws Exception {
        if (!isGzipStream(raw)) {
            return raw;
        }
        try (GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(raw));
             ByteArrayOutputStream out = new ByteArrayOutputStream()
        ) {
            IoUtils.copy(gis, out);
            return out.toByteArray();
        }
    }

    /**
     * Copy data.
     *
     * @param input  source
     * @param output target
     * @return copy size
     * @throws IOException io exception
     */
    public static long copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        int totalBytes = 0;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
            totalBytes += bytesRead;
        }

        return totalBytes;
    }

    /**
     * Judge whether is Gzip stream.
     *
     * @param bytes byte array
     * @return true if is gzip, otherwise false
     */
    public static boolean isGzipStream(byte[] bytes) {

        int minByteArraySize = 2;
        if (bytes == null || bytes.length < minByteArraySize) {
            return false;
        }

        return GZIPInputStream.GZIP_MAGIC == ((bytes[1] << 8 | bytes[0]) & 0xFFFF);
    }

}
