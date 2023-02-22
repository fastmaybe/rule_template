package com.sf.saas.bsp.rule.core.common.exception;

import com.sf.saas.bsp.rule.core.common.constans.ResponseCodeEnum;
import lombok.Getter;

/**
 * @author 01407460
 * @date 2022/9/7 10:20
 */
@Getter
public class BizException extends RuntimeException {

    /**
     * 国际化文件的code  备用
     */
    private String code;

    /**
     * 描述信息
     */
    private String msg;

    public BizException() {
        super();
    }

    public BizException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BizException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum.getMsg());
        this.code = responseCodeEnum.getCode();
        this.msg = responseCodeEnum.getMsg();
    }
}
