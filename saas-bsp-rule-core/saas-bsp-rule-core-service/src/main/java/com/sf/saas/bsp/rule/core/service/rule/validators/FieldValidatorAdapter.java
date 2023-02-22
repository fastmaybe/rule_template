package com.sf.saas.bsp.rule.core.service.rule.validators;

import cn.hutool.core.util.StrUtil;
import com.sf.saas.bsp.rule.core.service.rule.FieldValidator;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author 01407460
 * @date 2022/10/26 15:56
 */
public class FieldValidatorAdapter<T> implements FieldValidator<T>{

    private final FieldValidator targetValidator;

    private final Function<T, Object> transformFunction;

    public FieldValidatorAdapter(FieldValidator targetValidator, Function<T, Object> transformFunction) {
        this.targetValidator = targetValidator;
        this.transformFunction = transformFunction;
    }

    @Override
    public boolean validate(T fieldValue, List<String> configs) {
        Object t = transformFunction.apply(fieldValue);
        return targetValidator.validate(t, configs);
    }

    /**
     * 集合size
     * @param collection
     * @return
     */
    public static Integer collectionSize(Collection collection) {
        return Objects.isNull(collection) ? 0 : collection.size();
    }

    /**
     * 字符串长度
     * @param str
     * @return
     */
    public static Integer stringLength(String str) {
        return StrUtil.length(str);
    }
    
}
