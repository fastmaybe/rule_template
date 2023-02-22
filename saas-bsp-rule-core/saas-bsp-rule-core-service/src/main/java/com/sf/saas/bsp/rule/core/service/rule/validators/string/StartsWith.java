package com.sf.saas.bsp.rule.core.service.rule.validators.string;

import cn.hutool.core.util.StrUtil;
import com.sf.saas.bsp.rule.core.service.rule.FieldValidator;

import java.util.List;

/**
 * @author 01407460
 * @date 2022/10/26 15:56
 */
public class StartsWith implements FieldValidator<String> {



    @Override
    public boolean validate(String fieldValue, List<String> configs) {
        return StrUtil.startWith(fieldValue, configs.get(0));
    }
}
