package com.sf.saas.bsp.rule.core.service.event.redis.message;

import lombok.Data;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 01407460
 * @date 2022/9/8 16:02
 */

@Data
@Component
public class RedisPublisher<T extends MessageBase> implements InitializingBean {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisMessageListener redisMessageListener;

    public static final String REDIS_COMMON_TOPIC  = "bps-redis-common-topic";

    public void  publish(T t){
        RTopic topic = redissonClient.getTopic(REDIS_COMMON_TOPIC);
        topic.publish(t);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        RTopic topic = redissonClient.getTopic(REDIS_COMMON_TOPIC);
        topic.addListener(MessageBase.class,redisMessageListener);
    }
}
