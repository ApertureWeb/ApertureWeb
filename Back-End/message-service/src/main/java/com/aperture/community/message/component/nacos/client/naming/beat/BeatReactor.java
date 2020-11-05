package com.aperture.community.message.component.nacos.client.naming.beat;

import com.aperture.community.message.component.nacos.api.common.Constants;
import com.aperture.community.message.component.nacos.api.exception.NacosException;
import com.aperture.community.message.component.nacos.api.naming.CommonParams;
import com.aperture.community.message.component.nacos.api.naming.NamingResponseCode;
import com.aperture.community.message.component.nacos.api.naming.pojo.Instance;
import com.aperture.community.message.component.nacos.api.utils.NamingUtils;
import com.aperture.community.message.component.nacos.client.naming.net.NamingProxy;
import com.aperture.community.message.component.nacos.common.utils.JacksonUtils;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author HALOXIAO
 * @since 2020-10-27 16:39
 **/
public class BeatReactor {


    private final Logger logger = LoggerFactory.getLogger(BeatReactor.class);
    private final NamingProxy serverProxy;
    private final Vertx vertx;
    private boolean lightBeatEnabled = false;

    public final Map<String, BeatInfo> dom2Beat = new ConcurrentHashMap<String, BeatInfo>();


    public BeatReactor(NamingProxy serverProxy, Vertx vertx) {
        this.serverProxy = serverProxy;
        this.vertx = vertx;
    }

    /**
     * Add beat information.
     *
     * @param serviceName service name
     * @param beatInfo    beat information
     */

    //服务注册将服务心跳信息加入心跳反应器
    public void addBeatInfo(String serviceName, BeatInfo beatInfo) {
        logger.info("[BEAT] adding beat: {} to beat map.", beatInfo);

        String key = buildKey(serviceName, beatInfo.getIp(), beatInfo.getPort());
        BeatInfo existBeat = null;
        //fix #1733
        if ((existBeat = dom2Beat.remove(key)) != null) {
            existBeat.setStopped(true);
        }
        dom2Beat.put(key, beatInfo);
        //大约5s 发送一次心跳
        BeatTask beatTask = new BeatTask(beatInfo);
        beatTask.sendBeatTiming();
//        MetricsMonitor.getDom2BeatSizeMonitor().set(dom2Beat.size());
    }

    public BeatInfo buildBeatInfo(Instance instance) {
        return buildBeatInfo(instance.getServiceName(), instance);
    }

    /**
     * Build new beat information.
     *
     * @param groupedServiceName service name with group name, format: ${groupName}@@${serviceName}
     * @param instance           instance
     * @return new beat information
     */
    public BeatInfo buildBeatInfo(String groupedServiceName, Instance instance) {
        BeatInfo beatInfo = new BeatInfo();
        beatInfo.setServiceName(groupedServiceName);
        beatInfo.setIp(instance.getIp());
        beatInfo.setPort(instance.getPort());
        beatInfo.setCluster(instance.getClusterName());
        beatInfo.setWeight(instance.getWeight());
        beatInfo.setMetadata(instance.getMetadata());
        beatInfo.setScheduled(false);
        beatInfo.setPeriod(instance.getInstanceHeartBeatInterval());
        return beatInfo;
    }

    public String buildKey(String serviceName, String ip, int port) {
        return serviceName + Constants.NAMING_INSTANCE_ID_SPLITTER + ip + Constants.NAMING_INSTANCE_ID_SPLITTER + port;
    }


    /**
     * Remove beat information.
     *
     * @param serviceName service name
     * @param ip          ip of beat information
     * @param port        port of beat information
     */
    public void removeBeatInfo(String serviceName, String ip, int port) {
        logger.info("[BEAT] removing beat: {}:{}:{} from beat map.", serviceName, ip, port);
        BeatInfo beatInfo = dom2Beat.remove(buildKey(serviceName, ip, port));
        if (beatInfo == null) {
            return;
        }
        beatInfo.setStopped(true);
        //TODO 监控
        //        MetricsMonitor.getDom2BeatSizeMonitor().set(dom2Beat.size());
    }


    class BeatTask {
        BeatInfo beatInfo;
        long id;

        public BeatTask(BeatInfo beatInfo) {
            this.beatInfo = beatInfo;
        }

        public void sendBeatTiming() {
            this.id = vertx.setPeriodic(beatInfo.getPeriod(), handle -> {
                sendBeat();
            });
        }

        private void sendBeat() {
            if (beatInfo.isStopped()) {
                vertx.cancelTimer(id);
            }
            //Beat时间（ms）
            //发送心跳
            try {
                serverProxy.sendBeat(beatInfo, BeatReactor.this.lightBeatEnabled).onSuccess(result -> {
                    long nextTime = beatInfo.getPeriod();
                    //服务端对Beat间隔的控制
                    long interval = result.get("clientBeatInterval").asLong();
                    //lightBeatEnabled 表示这次Beat是否为轻量级Beat（即是否携带beat数据，默认只有第一次时才需要携带，第二次往后都是lighBeat）
                    boolean lightBeatEnabled = false;
                    if (result.has(CommonParams.LIGHT_BEAT_ENABLED)) {
                        lightBeatEnabled = result.get(CommonParams.LIGHT_BEAT_ENABLED).asBoolean();
                    }
                    BeatReactor.this.lightBeatEnabled = lightBeatEnabled;
                    //下一次心跳时间，默认5s
                    if (interval > 0) {
                        nextTime = interval;
                    }
                    int code = NamingResponseCode.OK;
                    if (result.has(CommonParams.CODE)) {
                        code = result.get(CommonParams.CODE).asInt();
                    }
                    //如果是404
                    if (code == NamingResponseCode.RESOURCE_NOT_FOUND) {
                        //需要注册的实例
                        Instance instance = new Instance();
                        instance.setPort(beatInfo.getPort());
                        instance.setIp(beatInfo.getIp());
                        instance.setWeight(beatInfo.getWeight());
                        instance.setMetadata(beatInfo.getMetadata());
                        instance.setClusterName(beatInfo.getCluster());
                        instance.setServiceName(beatInfo.getServiceName());
                        instance.setInstanceId(instance.getInstanceId());
                        instance.setEphemeral(true);
                        try {
                            serverProxy.registerService(beatInfo.getServiceName(),
                                    NamingUtils.getGroupName(beatInfo.getServiceName()), instance);
                        } catch (NacosException ignore) {

                        }
                    }

                }).compose(res -> Future.succeededFuture(true)
                ).onFailure(err ->
                        logger.error("[CLIENT-BEAT] failed to send beat: {} , msg: {}",
                                JacksonUtils.toJson(beatInfo), err.getMessage())
                );
            } catch (NacosException e) {
                logger.error("[CLIENT-BEAT] failed to send beat: {}, code: {}, msg: {}",
                        JacksonUtils.toJson(beatInfo), e.getErrCode(), e.getErrMsg());
            }

        }


    }
}