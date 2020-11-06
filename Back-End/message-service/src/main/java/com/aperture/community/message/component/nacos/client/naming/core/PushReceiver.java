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
        forePushTaskId = vertx.deployVerticle(new ForcePushTask());
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

    class ForcePushTask extends AbstractVerticle {

        @Override
        public void start() {
            io.vertx.core.datagram.DatagramSocket socket = vertx.createDatagramSocket(new DatagramSocketOptions());
            //TODO need to check if onec shot
            socket.listen(0, "0.0.0.0", asyncResult -> {
                if (asyncResult.succeeded()) {
                    socket.handler(packet -> {
                        String json = null;
                        try {
                            json = new String(IoUtils.tryDecompress(packet.data().getBytes()), UTF_8).trim();
                        } catch (Exception e) {
                            logger.info("received push data: " + json + " from " + packet.sender().hostAddress());
                        }
                        PushPacket pushPacket = JacksonUtils.toObj(json, PushPacket.class);
                        String ack;
                        if ("dom".equals(pushPacket.type) || "service".equals(pushPacket.type)) {
                            //更新一波服务信息先
                            hostReactor.processServiceJson(pushPacket.data);
                            //构造需要返回给server的数据
                            // send ack to server
                            ack = "{\"type\": \"push-ack\"" + ", \"lastRefTime\":\"" + pushPacket.lastRefTime + "\", \"data\":"
                                    + "\"\"}";
                        } else if ("dump".equals(pushPacket.type)) {
                            // dump data to server
                            ack = "{\"type\": \"dump-ack\"" + ", \"lastRefTime\": \"" + pushPacket.lastRefTime + "\", \"data\":"
                                    + "\"" + StringUtils.escapeJavaScript(JacksonUtils.toJson(hostReactor.getServiceInfoMap()))
                                    + "\"}";
                        } else {
                            // do nothing send ack only
                            ack = "{\"type\": \"unknown-ack\"" + ", \"lastRefTime\":\"" + pushPacket.lastRefTime
                                    + "\", \"data\":" + "\"\"}";
                        }

                        socket.send(ack, 0, packet.sender().hostAddress());
                    });
                } else {
                    logger.error("[NA] error while receiving push data", asyncResult.cause());
                }
            });
        }
    }


}
