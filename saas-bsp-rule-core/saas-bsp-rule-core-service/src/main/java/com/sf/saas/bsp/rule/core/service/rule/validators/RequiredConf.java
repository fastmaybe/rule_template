package com.sf.saas.bsp.rule.core.service.rule.validators;

import com.sf.saas.bsp.rule.core.service.rule.FieldValidator;

import java.util.List;

/**
 * @author 01407460
 * @date 2022/10/28 15:27
 */
public class RequiredConf implements FieldValidator<Object> {

    private IsNotNull isNotNull;

    @Override
    public boolean validate(Object fieldValue, List<String> configs) {
        Boolean p1 = getP1(configs, Boolean.class);
        if (Boolean.TRUE.equals(p1)){
            return isNotNull.validate(fieldValue, configs);
        }
        return true;
    }

    public RequiredConf(IsNotNull isNotNull) {
        this.isNotNull = isNotNull;
    }
}
