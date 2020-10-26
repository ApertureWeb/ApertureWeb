package com.aperture.community.message.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author HALOXIAO
 * @since 2020-10-19 17:49
 **/
@ConfigurationProperties("nacos")
@Configuration
@Data
public class NacosProperties {

    /**
     * nacos ip
     */
    private String ip;

    /**
     * nacos 端口
     */
    private Integer port;

    /**
     * 服务IP
     */
    private String serviceIp;

    /**
     * 服务端口
     * */
    private String servicePort;
    /**
     * 服务名
     */
    private String serviceName;

    /**
     * 分组名
     */
    private String groupName;

    /**
     * 集群名
     */
    private String clusterName;

    /**
     * 命名空间ID
     */
    private String namespaceId;

    /**
     * 权重
     */
    private Double weight;

    /**
     * 是否上线
     */
    private Boolean onlineEnabled;

    /**
     * 是否打开流量
     */
    private Boolean flowEnabled;

    /**
     * 是否只返回健康实例
     */
    private Boolean healthyOnly;

    /**
     * 是否健康
     */
    private Boolean healthy;

    /**
     * 是否临时实例
     */
    private Boolean ephemeral;

    /**
     * 拓展信息
     */
    private String metadata;

    /**
     * 实例心跳内容
     */
    private String beat;


    /**
     * 保护阈值，取值0~1，默认0
     */
    private Float protectThreshold = 0f;

    private Integer beatIntervalTime = 5000;

}
