package com.aperture.community.message.component;

import com.alibaba.fastjson.JSON;
import com.aperture.community.message.common.ServiceBusMap;
import com.aperture.community.message.module.dto.EventBusDto;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import jdk.jshell.EvalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * description: this component is used to register service
 *
 * @author HALOXIAO
 * @since 2020-10-20 12:13
 **/
@Component
@Slf4j
public class RegisterComponent extends AbstractVerticle {

    private final NacosClient nacosClient;
    private final Vertx vertx;

    public void register() {
        EventBus eventBus = vertx.eventBus();
        eventBus.consumer(ServiceBusMap.DISCOVERY_INSTANCE_REGISTER.name(), res -> {
            EventBusDto msg = JSON.parseObject(res.body().toString(), EventBusDto.class);
            if (!msg.isSuccess()) {
                log.error("注册失败" + msg.getMsg());
                System.exit(-1);
            }
        });
        nacosClient.registerInstance();


    }

    @Autowired
    public RegisterComponent(NacosClient nacosClient, Vertx vertx) {
        this.nacosClient = nacosClient;
        this.vertx = vertx;
    }

}
