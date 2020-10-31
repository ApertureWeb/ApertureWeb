package com.aperture.community.message.component.nacos.common.utils;

import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.parsetools.RecordParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


}
