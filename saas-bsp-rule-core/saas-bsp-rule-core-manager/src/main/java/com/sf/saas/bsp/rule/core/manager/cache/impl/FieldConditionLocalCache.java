package com.sf.saas.bsp.rule.core.manager.cache.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sf.saas.bsp.rule.core.common.constans.CommonStatusEnum;
import com.sf.saas.bsp.rule.core.dao.entity.RuleFieldCondition;
import com.sf.saas.bsp.rule.core.dao.mapper.RuleFieldConditionMapper;
import com.sf.saas.bsp.rule.core.dto.vo.FieldConditionSelectInfoVo;
import com.sf.saas.bsp.rule.core.manager.cache.IFieldConditionCache;
import com.sf.saas.bsp.rule.core.manager.convert.RuleConditionConvert;
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
public class FieldConditionLocalCache implements IFieldConditionCache {


    @Autowired
    private RuleFieldConditionMapper ruleFieldConditionMapper;

    private LoadingCache<String, Map<String, List<FieldConditionSelectInfoVo>>> fieldConditionCache = CacheBuilder.newBuilder()
            .initialCapacity(2)
            .expireAfterWrite(1, TimeUnit.DAYS)
            .build(new CacheLoader<String, Map<String, List<FieldConditionSelectInfoVo>>>() {
                @Override
                public Map<String, List<FieldConditionSelectInfoVo>> load(String key)  {
                    return selectConditionFromDb();
                }
            });


    @Override
    public Map<String, List<FieldConditionSelectInfoVo>> conditionList() {

        try {
           return fieldConditionCache.get("condition");
        } catch (ExecutionException e) {
            log.error("dictionaryGroupByLang error",e);
        }
        log.info("dictionaryGroupByLang cache error ,so get from db .");
        return selectConditionFromDb();
    }

    @Override
    public void cleanCache() {
        //清楚缓存
        fieldConditionCache.invalidateAll();
    }

    private Map<String, List<FieldConditionSelectInfoVo>> selectConditionFromDb(){
        LambdaQueryWrapper<RuleFieldCondition> eq = Wrappers.<RuleFieldCondition>lambdaQuery()
                .eq(RuleFieldCondition::getState, CommonStatusEnum.STATUS_ENABLED.getStatus());

        List<RuleFieldCondition> conditions = ruleFieldConditionMapper.selectList(eq);

        List<FieldConditionSelectInfoVo> dictionaryVos = RuleConditionConvert.convertDo2VoList(conditions);
        return dictionaryVos.stream().collect(Collectors.groupingBy(FieldConditionSelectInfoVo::getFieldKey));
    }
}
