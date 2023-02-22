package com.sf.saas.bsp.rule.core.service.rule.validators;



import com.sf.saas.bsp.rule.core.service.rule.FieldValidator;
import com.sf.saas.bsp.rule.core.service.rule.validators.string.IsNotBlank;

import java.util.List;
import java.util.Objects;

/**
 * @author 01407460
 * @date 2022/10/26 15:56
 */
public class IsNotNull implements FieldValidator<Object> {

    private IsNotBlank isNotBlank;


    @Override
    public boolean validate(Object fieldValue, List<String> configs) {
        if (fieldValue instanceof String){
            return isNotBlank.validate((String) fieldValue, configs);
        }
        return Objects.nonNull(fieldValue);
    }


    public IsNotNull(IsNotBlank isNotBlank) {
        this.isNotBlank = isNotBlank;
    }
}
