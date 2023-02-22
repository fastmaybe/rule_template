package com.sf.saas.bsp.rule.core.service.event.redis.message;

/**
 * @author 01407460
 * @date 2022/9/8 16:01
 */

public interface MessageBase  {

    /**
     * 链路
     * @return
     */
    String attachTraceId();
}
