package com.sf.saas.bsp.rule.core.service.rule.validators.string;

import cn.hutool.core.util.ReUtil;
import com.sf.saas.bsp.rule.core.service.rule.FieldValidator;

import java.util.List;
/**
 * @author 01407460
 * @date 2022/10/26 15:56
 */
public class Match implements FieldValidator<String> {


    @Override
    public boolean validate(String fieldValue, List<String> configs) {
        return ReUtil.isMatch(configs.get(0), fieldValue);
    }
}
