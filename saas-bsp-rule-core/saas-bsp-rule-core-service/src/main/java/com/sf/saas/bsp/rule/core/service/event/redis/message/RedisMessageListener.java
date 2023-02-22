package com.sf.saas.bsp.rule.core.service.event.redis.message;


import com.sf.saas.bsp.rule.core.common.constans.BspRuleConstant;
import com.sf.saas.bsp.rule.core.service.event.redis.handler.IMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.listener.MessageListener;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author 01407460
 * @date 2022/9/8 16:36
 */
@Slf4j
@Component
public class RedisMessageListener implements MessageListener<MessageBase> {


    @Autowired
    private List<IMessageHandler<MessageBase>> messageHandlerList;


    @Override
    public void onMessage(CharSequence charSequence, MessageBase publishBase) {
        try {
            String traceId = publishBase.attachTraceId();
            MDC.put(BspRuleConstant.TRACE_ID,traceId);
        } catch (IllegalArgumentException e) {
        }


        for (IMessageHandler<MessageBase> messageHandler : messageHandlerList) {
            if (messageHandler.support(publishBase)){
                try {
                    messageHandler.onMessage(publishBase);
                } catch (Exception e) {
                    log.error("RedisMessageListener error handler is {}",messageHandler.getClass().toString(),e);
                }
            }
        }
    }
}
