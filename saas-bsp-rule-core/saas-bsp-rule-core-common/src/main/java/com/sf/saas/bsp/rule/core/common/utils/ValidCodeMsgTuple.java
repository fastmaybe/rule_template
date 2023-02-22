package com.sf.saas.bsp.rule.core.common.utils;

import com.sf.saas.bsp.rule.core.common.constans.ResponseCodeEnum;
import lombok.Data;

/**
 * @author 01407460
 * @date 2022/7/27 20:00
 */
@Data
public class ValidCodeMsgTuple<T,D> {

    /**
     * 0 - 正常
     * > 0 - 有异常
     */
    private String code = "SUCCESS";

    /**
     * 失败返回错误
     */
    private T error;
    /**
     * 成功返回data
     */
    private D data;

    public ValidCodeMsgTuple(String code, T errorCode) {
        this.code = code;
        this.error = errorCode;
    }

    public ValidCodeMsgTuple(String code, T errorCode, D data) {
        this.code = code;
        this.error = errorCode;
        this.data = data;
    }

    public ValidCodeMsgTuple(D data) {
        this.data = data;
    }

    public ValidCodeMsgTuple() {
    }

    public static <T,D> ValidCodeMsgTuple<T,D> success(D data) {
        return new ValidCodeMsgTuple<>(data);
    }

    /**
     * code 默认是success
     * @param <T>
     * @param <D>
     * @return
     */
    public static <T,D> ValidCodeMsgTuple<T,D> success() {
        return new ValidCodeMsgTuple<>();
    }

    public static <T,D> ValidCodeMsgTuple<T,D> error(String code, T errorCode) {
        return new ValidCodeMsgTuple<>(code, errorCode);
    }

    public static <D> ValidCodeMsgTuple<ResponseCodeEnum, D> error(ResponseCodeEnum errorCode) {
        return new ValidCodeMsgTuple<>(errorCode.getCode(), errorCode);
    }

    public static <D> ValidCodeMsgTuple<ResponseCodeEnum, D> error(ResponseCodeEnum errorCode, D data) {
        return new ValidCodeMsgTuple<>(errorCode.getCode(), errorCode, data);
    }

    public boolean isOK() {
        return "SUCCESS".equals(this.code);
    }

    public boolean isErr() {
        return !isOK();
    }
}
