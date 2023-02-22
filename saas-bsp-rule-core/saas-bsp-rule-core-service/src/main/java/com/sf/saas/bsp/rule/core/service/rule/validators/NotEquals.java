package com.sf.saas.bsp.rule.core.service.rule.validators;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.sf.saas.bsp.rule.core.service.rule.FieldValidator;

import java.util.List;
import java.util.Objects;

/**
 * @author 01407460
 * @date 2022/10/26 15:56
 */
public class NotEquals implements FieldValidator<Object> {



    @Override
    public boolean validate(Object fieldValue, List<String> configs) {
        return Objects.nonNull(fieldValue) && !Objects.equals(fieldValue, Convert.convertQuietly(fieldValue.getClass(), StrUtil.trim(configs.get(0))));
    }
}
