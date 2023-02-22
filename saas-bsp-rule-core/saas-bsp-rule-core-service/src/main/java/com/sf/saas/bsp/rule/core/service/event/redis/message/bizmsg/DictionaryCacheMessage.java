package com.sf.saas.bsp.rule.core.service.event.redis.message.bizmsg;

import cn.hutool.core.net.NetUtil;
import com.sf.saas.bsp.rule.core.common.constans.BspRuleConstant;
import com.sf.saas.bsp.rule.core.service.event.redis.message.MessageBase;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.io.Serializable;

/**
 * @author 01407460
 * @date 2022/9/8 17:00
 */
@Slf4j
@Data
public class DictionaryCacheMessage  implements MessageBase, Serializable {

    private String tranceId;

    private String sourceHostName;

    @Override
    public String attachTraceId() {
        return this.getTranceId();
    }

    public DictionaryCacheMessage() {
        try {
            this.tranceId = MDC.get(BspRuleConstant.TRACE_ID);
            this.sourceHostName = NetUtil.getLocalHostName();
        } catch (IllegalArgumentException e) {
           log.warn("new DictionaryCacheMessage error",e);
        }
    }
}
