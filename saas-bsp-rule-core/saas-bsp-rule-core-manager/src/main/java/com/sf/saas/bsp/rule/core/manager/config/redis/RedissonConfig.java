package com.sf.saas.bsp.rule.core.manager.config.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 01407460
 * @date 2022/9/7 9:55
 */
@EnableConfigurationProperties(RedisProperties.class)
@Configuration
public class RedissonConfig {

    private static final String NODE_PRE = "redis://";


    @Autowired
    private RedisProperties redisProperties;

    @Bean
    @ConditionalOnProperty(name = "spring.redis.type", havingValue = "CLUSTER")
    public RedissonClient redissonClientForCluster() {
        Config config = new Config();
        //选择集群模式，添加节点，设置密码
        config.useClusterServers()
                .addNodeAddress(getNodes())
                .setPassword(redisProperties.getPassword());
        return Redisson.create(config);
    }





    @Bean
    @ConditionalOnProperty(name = "spring.redis.type", havingValue = "SENTINEL")
    public RedissonClient redissonClientForSENTINEL() {
        Config config = new Config();
        //选择哨兵模式，添加节点，设置密码
        config.useSentinelServers().addSentinelAddress(getNodes()).setMasterName(redisProperties.getMaster())
                .setDatabase(redisProperties.getDatabase())
                .setSlaveConnectionMinimumIdleSize(3)
                .setMasterConnectionMinimumIdleSize(1)
                .setPingConnectionInterval(6000)
                .setPassword(redisProperties.getPassword());
        return Redisson.create(config);
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.redis",name = "type", havingValue = "REDIS")
    public RedissonClient redissonClientForSingle() {
        Config config = new Config();
        //选择单机模式，添加节点，设置密码
        config.useSingleServer()
                .setAddress(getNodes()[0])
                .setPassword(redisProperties.getPassword())
                .setDatabase(redisProperties.getDatabase());
        return Redisson.create(config);
    }


    private String[] getNodes() {
        String[] nodes = redisProperties.getNodes().split(",");
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = NODE_PRE + nodes[i];
        }
        return nodes;
    }



}
