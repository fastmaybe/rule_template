package com.sf.saas.bsp.rule.core.service.rule;

/**
 * @author 01407460
 * @date 2022/10/26 15:19
 */
@FunctionalInterface
public interface Validator<T,R> {

    /**
     * 校验
     * @param target 目标对象
     * @return
     */
    R isValidate(T target);
}
