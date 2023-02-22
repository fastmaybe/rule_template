package com.sf.saas.bsp.rule.core.common.constans;

import lombok.Getter;

/**
 * @author 01407460
 * @date 2022/11/3 15:36
 */

@Getter
public enum RuleTypeEnum {

    /**
     * 基础
     */
    BASICS(1),

    /**
     * 复合
     */
    COMPOUND(2),

    /**
     * 特殊
     */
    SPECIAL(3),


    ;

    private Integer type;

    RuleTypeEnum(Integer type) {
        this.type = type;
    }
}
