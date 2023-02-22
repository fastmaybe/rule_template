package com.sf.saas.bsp.rule.core.service.rule.biz.rulekey;

import cn.hutool.json.JSON;
import org.springframework.stereotype.Component;

/**
 * @author 01407460
 * @date 2022/10/28 14:53
 */
@Component
public class TestRuleKeyValidator  implements RuleKeyValidator{
    @Override
    public Boolean isValidate(JSON target) {
        return true;
    }

    @Override
    public RuleKeyEnum support() {
        return RuleKeyEnum.RULE_KEY_TEST1_TEST;
    }
}
