package com.sf.saas.bsp.rule.core.dto.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 01407460
 * @date 2022/10/12 16:02
 */
@Data
public class AppResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 返回code
     */
    private String codeMsg;
    /**
     * 内容
     */
    private T obj;

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
