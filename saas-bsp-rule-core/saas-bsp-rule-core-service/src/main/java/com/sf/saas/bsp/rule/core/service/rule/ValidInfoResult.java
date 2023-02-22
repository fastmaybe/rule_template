package com.sf.saas.bsp.rule.core.service.rule;

import lombok.Data;

/**
 * @author 01407460
 * @date 2022/10/27 17:13
 */
@Data
public class ValidInfoResult  implements ValidResult {

    /**
     * 是否 通过
     */
    private boolean validate;

    private String validMsg;

    @Override
    public boolean validate() {
        return this.validate;
    }

    public static ValidInfoResult buildPass(){
        ValidInfoResult result = new ValidInfoResult();
        result.setValidate(true);
        return result;
    }

    public static ValidInfoResult buildFail(String validMsg){
        ValidInfoResult result = new ValidInfoResult();
        result.setValidate(false);
        result.setValidMsg(validMsg);
        return result;
    }
}
