package com.sf.saas.bsp.rule.core.service.rule;

import lombok.Data;

/**
 * @author 01407460
 * @date 2022/10/27 15:15
 */
@Data
public class ValidBaseResult  implements ValidResult{

    /**
     * 是否 通过
     */
    private boolean validate;

    @Override
    public boolean validate() {
        return this.validate;
    }

    public static ValidResult buildPass(){
        ValidBaseResult result = new ValidBaseResult();
        result.setValidate(true);
        return result;
    }

    public static ValidResult build(boolean flag){
        ValidBaseResult result = new ValidBaseResult();
        result.setValidate(flag);
        return result;
    }

    public static ValidResult buildFail(){
        ValidBaseResult result = new ValidBaseResult();
        result.setValidate(false);
        return result;
    }
}
