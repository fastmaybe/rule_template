package com.sf.saas.bsp.rule.core.dto.enums;

/**
 * Description ValueRangeConditionEnum
 *
 * @author suntao(01408885)
 * @since 2022-11-10
 */
public enum ValueRangeConditionEnum {

    BETWEEN_WITH_BOTH("BETWEEN_WITH_BOTH"),
    IN_CONF("IN_CONF"),
    GREATER("GREATER"),
    LESS("LESS");

    private String condition;

    ValueRangeConditionEnum(String lang) {
        this.condition = lang;
    }

    public String getCondition() {
        return condition;
    }
}
