package com.sf.saas.bsp.rule.core.service.rule;
import cn.hutool.json.JSON;
/**
 * @author 01407460
 * @date 2022/10/26 15:22
 */


@FunctionalInterface
public interface PreValidator extends Validator<JSON, Boolean> {

    @Override
    Boolean isValidate(JSON target);
}
