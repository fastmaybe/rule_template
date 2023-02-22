package com.sf.saas.bsp.rule.core.manager.cache.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sf.saas.bsp.rule.core.common.constans.CommonStatusEnum;
import com.sf.saas.bsp.rule.core.dao.entity.RuleDictionary;
import com.sf.saas.bsp.rule.core.dao.mapper.RuleDictionaryMapper;
import com.sf.saas.bsp.rule.core.dto.vo.DictionarySelectVo;
import com.sf.saas.bsp.rule.core.manager.cache.IDictionaryCache;
import com.sf.saas.bsp.rule.core.manager.convert.DictionaryConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author 01407460
 * @date 2022/9/7 9:36
 */

@Slf4j
@Component
public class DictionaryLocalCache implements IDictionaryCache {


    @Autowired
    private RuleDictionaryMapper dictionaryMapper;

    private LoadingCache<String, Map<String, List<DictionarySelectVo>>> dictionaryCache = CacheBuilder.newBuilder()
            .initialCapacity(2)
            .expireAfterWrite(1, TimeUnit.DAYS)
            .build(new CacheLoader<String, Map<String, List<DictionarySelectVo>>>() {
                @Override
                public Map<String, List<DictionarySelectVo>> load(String key)  {
                    return dictionaryGroupByLangFromDb(key);
                }
            });


    @Override
    public Map<String, List<DictionarySelectVo>> dictionaryGroupByLang(String currentLang) {

        try {
           return dictionaryCache.get(currentLang);
        } catch (ExecutionException e) {
            log.error("dictionaryGroupByLang error",e);
        }
        log.info("dictionaryGroupByLang cache error ,so get from db .");
        return dictionaryGroupByLangFromDb(currentLang);
    }

    @Override
    public void cleanCache() {
        //清楚缓存
        dictionaryCache.invalidateAll();
    }

    private Map<String, List<DictionarySelectVo>> dictionaryGroupByLangFromDb(String lang){
        LambdaQueryWrapper<RuleDictionary> eq = Wrappers.<RuleDictionary>lambdaQuery()
                .eq(RuleDictionary::getStatus, CommonStatusEnum.STATUS_ENABLED.getStatus())
                .eq(RuleDictionary::getLang, lang);

        List<RuleDictionary> dictionaries = dictionaryMapper.selectList(eq);
        List<DictionarySelectVo> dictionaryVos = DictionaryConvert.convertDo2VoList(dictionaries);
        return dictionaryVos.stream().collect(Collectors.groupingBy(DictionarySelectVo::getScene));
    }
}
