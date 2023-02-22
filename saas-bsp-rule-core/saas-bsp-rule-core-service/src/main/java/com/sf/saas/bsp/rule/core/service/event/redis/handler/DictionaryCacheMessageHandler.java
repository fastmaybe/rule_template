package com.sf.saas.bsp.rule.core.service.event.redis.handler;

import com.alibaba.fastjson.JSON;

import com.sf.saas.bsp.rule.core.manager.cache.IDictionaryCache;
import com.sf.saas.bsp.rule.core.service.event.redis.message.MessageBase;
import com.sf.saas.bsp.rule.core.service.event.redis.message.bizmsg.DictionaryCacheMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 01407460
 * @date 2022/9/8 17:04
 */
@Slf4j
@Component
public class DictionaryCacheMessageHandler implements IMessageHandler<MessageBase> {

    @Autowired
    private IDictionaryCache dictionaryCache;


    @Override
    public boolean support(MessageBase messageBase) {
        return messageBase instanceof DictionaryCacheMessage;
    }

    @Override
    public void onMessage(MessageBase messageBase) {
        DictionaryCacheMessage cacheMessage = (DictionaryCacheMessage) messageBase;
        log.info("[redis message-DictionaryCacheMessage] is {} .", JSON.toJSON(cacheMessage));

        //do something
        boolean needIgnore = needIgnore(cacheMessage.getSourceHostName());
        if (needIgnore){
            log.info("DictionaryCacheMessageHandler found source is self so return");
            return;
        }
        // 重加载
        dictionaryCache.cleanCache();
    }
}
