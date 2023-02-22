package com.sf.saas.bsp.rule.core.service.rule.validators;



import com.sf.saas.bsp.rule.core.service.rule.FieldValidator;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author 01407460
 * @date 2022/10/26 15:56
 */
public class NotInConf implements FieldValidator<Object> {



    @Override
    public boolean validate(Object fieldValue, List<String> configs) {
        if (Objects.isNull(fieldValue)){
            // 不包含  当此字段为空  默认  满足不在此配置集合  返回true
            return true;
        }
        Set<Object> set = configToSet(configs, (Class<Object>) fieldValue.getClass());
        return !set.contains(fieldValue);
    }
}
