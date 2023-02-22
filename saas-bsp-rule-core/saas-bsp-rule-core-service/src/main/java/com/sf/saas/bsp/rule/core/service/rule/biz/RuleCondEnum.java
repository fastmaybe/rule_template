package com.sf.saas.bsp.rule.core.service.rule.biz;

import lombok.Getter;

/**
 * @author 01407460
 * @date 2022/11/1 10:53
 */
@SuppressWarnings("all")
@Getter
public enum RuleCondEnum {

    WAYBILLNO("waybillNo"),

    /**
     * 月结白名单
     */
    MONTH_CARD_NO_WHITE("monthCardNoWhite"),


    /**
     * 下单渠道
     */
    ORDER_CHANNEL("orderChannel"),

    ;

    /**
     * 字段
     */
    private String field;


    RuleCondEnum(String field) {
        this.field = field;
    }
}
