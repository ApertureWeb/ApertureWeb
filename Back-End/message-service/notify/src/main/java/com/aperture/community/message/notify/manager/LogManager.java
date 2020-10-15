package com.aperture.community.message.notify.manager;


import io.vertx.core.AbstractVerticle;

/**
 * two implement
 * OSS: {@link com.aperture.community.message.notify.manager.impl.LogManagerOSSImpl}
 * HDFS: {@link com.aperture.community.message.notify.manager.impl.LogManagerHDFSImpl}
 */
public interface LogManager   {

    void logMessage(String message);

}
