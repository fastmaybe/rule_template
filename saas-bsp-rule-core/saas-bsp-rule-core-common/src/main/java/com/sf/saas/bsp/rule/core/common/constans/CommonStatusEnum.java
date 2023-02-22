package com.sf.saas.bsp.rule.core.common.constans;

/**
 * @author 01407460
 * @date 2022/9/8 11:00
 */
public enum CommonStatusEnum {

    /**
     * 不可用
     */
    STATUS_NOT_ENABLED(0),
    /**
     * 可用
     */
    STATUS_ENABLED(1);

    private Integer status;

    CommonStatusEnum(int status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
