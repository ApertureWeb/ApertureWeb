package com.aperture.community.message.component.nacos.client.naming.core.verticle;

import com.aperture.community.message.component.nacos.client.naming.core.PushReceiver;
import com.aperture.community.message.component.nacos.common.utils.IoUtils;
import com.aperture.community.message.component.nacos.common.utils.JacksonUtils;
import com.aperture.community.message.component.nacos.common.utils.StringUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.datagram.DatagramSocketOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.nio.charset.StandardCharsets.UTF_8;


public class ForcePushTask extends AbstractVerticle {

    private static final Logger logger = LoggerFactory.getLogger(ForcePushTask.class);

    //TODO 优化部署方式
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
                    PushReceiver.PushPacket pushPacket = JacksonUtils.toObj(json, PushReceiver.PushPacket.class);
                    String ack;
                    if ("dom".equals(pushPacket.type) || "service".equals(pushPacket.type)) {
                        //更新一波服务信息先
                        //TODO  bus
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




