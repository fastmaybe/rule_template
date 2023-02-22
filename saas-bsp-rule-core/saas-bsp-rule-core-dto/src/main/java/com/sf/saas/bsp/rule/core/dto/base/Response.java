package com.sf.saas.bsp.rule.core.dto.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 01407460
 * @date 2022/9/7 10:32
 */
@Data
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 返回code
     */
    private String code;
    /**
     * 内容
     */
    private T data;

    /**
     * 备用
     */
    private String msg;


    /**
     * 链路
     */
    private String traceId;

    public boolean isSuccess() {
        return this.success;
    }


}
