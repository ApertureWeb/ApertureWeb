package com.aperture.community.message.component.nacos.client.naming.beat;

import com.aperture.community.message.component.nacos.api.common.Constants;
import com.aperture.community.message.component.nacos.client.naming.net.NamingProxy;
import com.aperture.community.message.component.nacos.client.naming.utils.UtilAndComs;
import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author HALOXIAO
 * @since 2020-10-27 16:39
 **/
public class BeatReactor {


    private final ScheduledExecutorService executorService;
    private final Logger logger = LoggerFactory.getLogger(BeatReactor.class);
    private final NamingProxy serverProxy;

    private boolean lightBeatEnabled = false;

    public final Map<String, BeatInfo> dom2Beat = new ConcurrentHashMap<String, BeatInfo>();

    public BeatReactor(NamingProxy serverProxy) {
        this(serverProxy, UtilAndComs.DEFAULT_CLIENT_BEAT_THREAD_COUNT);
    }

    public BeatReactor(NamingProxy serverProxy, int threadCount) {
        this.serverProxy = serverProxy;
        //定时任务线程池
        this.executorService = new ScheduledThreadPoolExecutor(threadCount, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                //标记为守护线程
                thread.setDaemon(true);
                thread.setName("com.alibaba.nacos.naming.beat.sender");
                return thread;
            }
        });
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
        executorService.schedule(new BeatTask(beatInfo), beatInfo.getPeriod(), TimeUnit.MILLISECONDS);
//        MetricsMonitor.getDom2BeatSizeMonitor().set(dom2Beat.size());
    }

    public String buildKey(String serviceName, String ip, int port) {
        return serviceName + Constants.NAMING_INSTANCE_ID_SPLITTER + ip + Constants.NAMING_INSTANCE_ID_SPLITTER + port;
    }

    class BeatTask extends AbstractVerticle {
        BeatInfo beatInfo;
        public BeatTask(BeatInfo beatInfo){
            this.beatInfo = beatInfo;
        }

        @Override
        public void start() throws Exception {
            if(beatInfo.isStopped()){
                return;
            }
            long nextTime = beatInfo.getPeriod();

        }
    }

}
