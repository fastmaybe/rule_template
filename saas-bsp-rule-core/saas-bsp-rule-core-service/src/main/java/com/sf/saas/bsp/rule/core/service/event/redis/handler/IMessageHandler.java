package com.sf.saas.bsp.rule.core.service.event.redis.handler;

import cn.hutool.core.net.NetUtil;
import com.sf.saas.bsp.rule.core.service.event.redis.message.MessageBase;

import java.util.Objects;

/**
 * @author 01407460
 * @date 2022/9/8 17:04
 */
public interface IMessageHandler<T extends MessageBase> {

    boolean support(T t);

   void onMessage(T t);

   default boolean needIgnore(String sourceHostName){
       String localHostName = NetUtil.getLocalHostName();
       return Objects.equals(sourceHostName,localHostName);
   }
}
