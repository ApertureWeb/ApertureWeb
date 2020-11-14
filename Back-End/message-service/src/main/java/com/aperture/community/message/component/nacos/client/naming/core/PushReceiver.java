package com.aperture.community.message.component.nacos.client.naming.core;

import com.aperture.community.message.component.nacos.common.utils.IoUtils;
import com.aperture.community.message.component.nacos.common.utils.JacksonUtils;
import com.aperture.community.message.component.nacos.common.utils.StringUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.datagram.DatagramSocketOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramSocket;
import java.nio.charset.Charset;

/**
 * @author HALOXIAO
 * @since 2020-10-27 16:37
 **/
public class PushReceiver implements Closeable {

    private final Logger logger = LoggerFactory.getLogger(PushReceiver.class);

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private static final int UDP_MSS = 64 * 1024;
    private final Vertx vertx;
    private Future<String> forePushTaskId;
    private DatagramSocket udpSocket;

    private HostReactor hostReactor;


    public PushReceiver(HostReactor hostReactor, Vertx vertx) {
        this.hostReactor = hostReactor;
        this.vertx = vertx;
        try {
            this.udpSocket = new DatagramSocket();
        } catch (Exception e) {
            logger.error("[NA] init udp socket failed", e);
        }
        forePushTaskId = vertx.deployVerticle("verticle/ForcePushTask.java");
    }

    public int getUdpPort() {
        return this.udpSocket.getLocalPort();
    }

    public static class PushPacket {
        public String type;
        public long lastRefTime;
        public String data;
    }

    @Override
    public void close() {
        logger.info("PushReceiver shutdown begin");
        forePushTaskId.onSuccess(vertx::undeploy);
        logger.info("PushReceiver shutdown stop");
    }



}
