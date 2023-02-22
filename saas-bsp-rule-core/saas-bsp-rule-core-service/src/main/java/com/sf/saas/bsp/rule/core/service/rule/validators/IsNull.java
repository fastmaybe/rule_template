package com.sf.saas.bsp.rule.core.service.rule.validators;


import com.sf.saas.bsp.rule.core.service.rule.FieldValidator;
import com.sf.saas.bsp.rule.core.service.rule.validators.string.IsBlank;

import java.util.List;
import java.util.Objects;

/**
 * @author 01407460
 * @date 2022/10/26 15:56
 */
public class IsNull implements FieldValidator<Object> {

    private IsBlank isBlank;


    @Override
    public boolean validate(Object fieldValue, List<String> configs) {
        if (fieldValue instanceof String){
            return isBlank.validate((String) fieldValue, configs);
        }
        return Objects.isNull(fieldValue);
    }

    public IsNull(IsBlank isBlank) {
        this.isBlank = isBlank;
    }
}
