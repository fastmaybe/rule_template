package com.sf.saas.bsp.rule.core.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统运行时异常
 *
 * @author 80003774
 * @since 1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemException extends RuntimeException {

    public static final Integer ERROR_CODE = 500;

    private final String errorMsg;

    public SystemException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
        this.errorMsg = message;
    }

}
