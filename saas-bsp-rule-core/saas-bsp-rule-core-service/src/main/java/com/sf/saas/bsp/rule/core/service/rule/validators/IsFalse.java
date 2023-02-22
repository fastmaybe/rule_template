package com.sf.saas.bsp.rule.core.service.rule.validators;



import com.sf.saas.bsp.rule.core.service.rule.FieldValidator;

import java.util.List;

/**
 * @author 01407460
 * @date 2022/10/26 15:56
 */
public class IsFalse implements FieldValidator<Boolean> {



    @Override
    public boolean validate(Boolean fieldValue, List<String> configs) {
        return Boolean.FALSE.equals(fieldValue);
    }
}
