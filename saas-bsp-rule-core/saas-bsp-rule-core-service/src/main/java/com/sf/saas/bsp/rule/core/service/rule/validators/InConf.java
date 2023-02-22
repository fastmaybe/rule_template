package com.sf.saas.bsp.rule.core.service.rule.validators;



import com.sf.saas.bsp.rule.core.service.rule.FieldValidator;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author 01407460
 * @date 2022/10/26 15:56
 */
public class InConf implements FieldValidator<Object> {



    @Override
    public boolean validate(Object fieldValue, List<String> configs) {
        if (Objects.isNull(fieldValue)){
            // 当为空  直接默认 不包含  直接返回 false
            return false;
        }
        Set<Object> set =  configToSet(configs, (Class<Object>) fieldValue.getClass());
        return set.contains(fieldValue);
    }
}
