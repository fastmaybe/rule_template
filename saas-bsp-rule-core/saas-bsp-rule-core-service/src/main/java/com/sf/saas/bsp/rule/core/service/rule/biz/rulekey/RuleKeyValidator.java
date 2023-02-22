package com.sf.saas.bsp.rule.core.service.rule.biz.rulekey;

import cn.hutool.json.JSON;
import com.sf.saas.bsp.rule.core.service.rule.Validator;
import com.sf.saas.bsp.rule.core.service.rule.ValidatorSupport;

/**
 * @author 01407460
 * @date 2022/10/28 14:49
 */
public interface RuleKeyValidator extends Validator<JSON, Boolean>, ValidatorSupport<RuleKeyEnum> {

}
