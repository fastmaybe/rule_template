package com.sf.saas.bsp.rule.core.common.utils;

import com.sf.saas.bsp.rule.core.common.constans.ResponseCodeEnum;
import lombok.Data;

/**
 * @author 01407460
 * @date 2022/7/27 20:00
 */
@Data
public class ValidCodeMsg<T> {

    /**
     * 0 - 正常
     * > 0 - 有异常
     */
    private String code = "SUCCESS";

    /**
     * 0 时候- 业务信息
     * 大于0 时候- 异常信息
     */
    private T data;

    public ValidCodeMsg(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public ValidCodeMsg(T data) {
        this.data = data;
    }

    public ValidCodeMsg() {
    }

    public static <T> ValidCodeMsg<T> success(T data) {
        return new ValidCodeMsg<>(data);
    }

    public static <T> ValidCodeMsg<T> success() {
        return new ValidCodeMsg<>();
    }

    public static <T> ValidCodeMsg<T> error(String code, T data) {
        return new ValidCodeMsg<>(code, data);
    }

    public static ValidCodeMsg<ResponseCodeEnum> error(ResponseCodeEnum errorCode) {
        return new ValidCodeMsg<>(errorCode.getCode(), errorCode);
    }

    public boolean isOK() {
        return "SUCCESS".equals(this.code);
    }

    public boolean isErr() {
        return !isOK();
    }
}
