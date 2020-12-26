package com.aperture.community.member.config;

import com.aperture.community.member.config.properties.RedisConfigProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: JayV
 * @Date: 2020-10-28 12:01
 * @Description: redisson集群模式配置类
 */
@Configuration
public class RedissonConfig {

    @Autowired
    RedisConfigProperties redisConfigProperties;

    @Bean(destroyMethod = "")
    public RedissonClient redisson() {
        List<String> clusterNodes = new ArrayList<>();
        redisConfigProperties.getCluster().getNodes().stream().filter(node ->
            clusterNodes.add("redis://" + node)
        );

        Config config = new Config();
        ClusterServersConfig clusterServersConfig = config.useClusterServers().addNodeAddress(clusterNodes.toArray(new String[clusterNodes.size()]));

        return Redisson.create(config);
    }

}