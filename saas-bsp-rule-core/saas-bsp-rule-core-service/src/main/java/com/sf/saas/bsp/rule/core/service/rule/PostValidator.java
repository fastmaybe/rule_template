package com.sf.saas.bsp.rule.core.service.rule;

import com.sf.saas.bsp.rule.core.service.rule.bo.RuleParam;

import java.util.List;

/**
 * @author 01407460
 * @date 2022/10/26 15:28
 */
public interface PostValidator extends Validator<RuleParam, Boolean>, ValidatorSupport<List<Integer>> {


    @Override
    Boolean isValidate(RuleParam target);


    @Override
    List<Integer> support();
}
