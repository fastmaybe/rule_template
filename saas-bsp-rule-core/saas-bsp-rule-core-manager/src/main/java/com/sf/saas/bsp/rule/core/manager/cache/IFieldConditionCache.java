package com.sf.saas.bsp.rule.core.manager.cache;

/**
 * @author 01407460
 * @date 2022/9/7 9:35
 */

import com.sf.saas.bsp.rule.core.dto.vo.FieldConditionSelectInfoVo;

import java.util.List;
import java.util.Map;

/**
 * 字典 cache
 */
public interface IFieldConditionCache {

    /**
     * 根据语言 获取字典组
     */
    Map<String, List<FieldConditionSelectInfoVo>> conditionList();


    /**
    清除 字典 缓存 缓存
     */
    void cleanCache();
}
