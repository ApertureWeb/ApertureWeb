package com.aperture.community.member.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: JayV
 * @Date: 2020-11-13 19:42
 * @Description: 使用yml文件里的配置内容
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfigProperties {

//    private String password;
    private cluster cluster;

    public static class cluster {
        private List<String> nodes;

        public List<String> getNodes() {
            return nodes;
        }

        public void setNodes(List<String> nodes) {
            this.nodes = nodes;
        }
    }

    public RedisConfigProperties.cluster getCluster() {
        return cluster;
    }

    public void setCluster(RedisConfigProperties.cluster cluster) {
        this.cluster = cluster;
    }
}