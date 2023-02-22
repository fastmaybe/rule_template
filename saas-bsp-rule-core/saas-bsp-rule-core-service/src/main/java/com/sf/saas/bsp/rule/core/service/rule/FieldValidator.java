package com.sf.saas.bsp.rule.core.service.rule;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 01407460
 * @date 2022/10/26 16:10
 */
public interface FieldValidator<T> {

    /**
     * 校验
     *
     * @param fieldValue
     * @param configs
     * @return
     */
    boolean validate(T fieldValue, List<String> configs);


    /**
     * 获取配置
     *
     * @param configs
     * @param index
     * @param type
     * @param <T>
     * @return
     */
    default <T> T getConfig(List<String> configs, int index, Class<T> type) {
        return Convert.convert(type, StrUtil.trim(configs.get(index)));
    }

    /**
     * 获取第一个参数
     *
     * @param configs
     * @param type
     * @param <T>
     * @return
     */
    default <T> T getP1(List<String> configs, Class<T> type) {
        return getConfig(configs, 0, type);
    }

    default BigDecimal getP1Num(List<String> configs) {
        return getConfig(configs, 0, BigDecimal.class);
    }

    default BigDecimal getP2Num(List<String> configs) {
        return getConfig(configs, 1, BigDecimal.class);
    }

    /**
     * 获取第二个参数
     *
     * @param configs
     * @param type
     * @param <T>
     * @return
     */
    default <T> T getP2(List<String> configs, Class<T> type) {
        return getConfig(configs, 1, type);
    }

    /**
     * 配置参数转换为 set
     *
     * @param configs
     * @param type
     * @param <T>
     * @return
     */
    default <T> Set<T> configToSet(List<String> configs, Class<T> type) {
        if (CollUtil.isEmpty(configs)) {
            return Collections.emptySet();
        }
        Set<T> set = configs.stream()
                .filter(StrUtil::isNotBlank)
                .map(conf -> Convert.convertQuietly(type, StrUtil.trim(conf)))
                .filter(Objects::nonNull)
                .map(type::cast)
                .collect(Collectors.toSet());
        return set;
    }
}
