package com.sf.saas.bsp.rule.core.common.emnus;

/**
 * @author 01407460
 * @date 2022/9/20 14:25
 */
public enum RequestTypeEnum {


    /**
     * 高峰管控
     */
    CONTROL(1),

 ;

    private Integer type;

    RequestTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getValue() {
        return type;
    }
}
