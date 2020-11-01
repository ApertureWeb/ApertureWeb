package com.aperture.community.message.component.nacos.client.naming.core;

import com.aperture.community.message.component.nacos.common.utils.IoUtils;
import com.aperture.community.message.component.nacos.common.utils.JacksonUtils;
import com.aperture.community.message.component.nacos.common.utils.StringUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.datagram.DatagramSocketOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.Charset;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/**
 * @author HALOXIAO
 * @since 2020-10-27 16:37
 **/
public class PushReceiver implements Cloneable {

    private final Logger PUSH_RECEIVER_LOGGER = LoggerFactory.getLogger(PushReceiver.class);

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private static final int UDP_MSS = 64 * 1024;

    private ScheduledExecutorService executorService;

    private DatagramSocket udpSocket;

    private HostReactor hostReactor;

    private volatile boolean closed = false;


    public PushReceiver(HostReactor hostReactor) {
        try {
            this.hostReactor = hostReactor;
            this.udpSocket = new DatagramSocket();
            this.executorService = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setDaemon(true);
                    thread.setName("com.alibaba.nacos.naming.push.receiver");
                    return thread;
                }
            });
        } catch (Exception e) {
            PUSH_RECEIVER_LOGGER.error("[NA] init udp socket failed", e);
        }
    }

    public static class PushPacket {

        public String type;

        public long lastRefTime;

        public String data;
    }

    class hardPushTask extends AbstractVerticle {

        @Override
        public void start() throws Exception {
            io.vertx.core.datagram.DatagramSocket socket = vertx.createDatagramSocket(new DatagramSocketOptions());

            socket.listen(0, "0.0.0.0", asyncResult -> {
                if (asyncResult.succeeded()) {
                    socket.handler(packet -> {
                        String json = new String(IoUtils.tryDecompress(packet.data().getBytes()), UTF_8).trim();
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

                        udpSocket.send(new DatagramPacket(ack.getBytes(UTF_8), ack.getBytes(UTF_8).length,
                                packet.sender().hostAddress().getSocketAddress()));
                        socket.send(ack,0,packet.sender().hostAddress());
                    });
                } else {
                    System.out.println("Listen failed" + asyncResult.cause());
                }
            });
        }
    }


}
