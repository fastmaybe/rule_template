package com.sf.saas.bsp.rule.core.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * redis 锁定异常，重复请求
 *
 * @author 80003774
 * @since 1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LockException extends RuntimeException {

    public static final Integer ERROR_CODE = 204;

    private final String errorMsg;

    public LockException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public LockException(String message, Throwable cause) {
        super(message, cause);
        this.errorMsg = message;
    }

}
