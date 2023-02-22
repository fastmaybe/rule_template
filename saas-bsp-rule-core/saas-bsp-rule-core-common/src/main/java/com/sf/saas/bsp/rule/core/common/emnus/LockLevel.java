package com.sf.saas.bsp.rule.core.common.emnus;

/**
 * Redis锁等级
 *
 * @author 80003774
 * @since 1.8
 */
public enum LockLevel {

    /**
     * 实例
     */
    INSTANCE,
    // 类
    CLASS,
    // 方法
    METHOD,
    // 字段
    FIELD,
    // 请求
    REQUEST,
    // 全局数据锁定
    GLOBAL

}
