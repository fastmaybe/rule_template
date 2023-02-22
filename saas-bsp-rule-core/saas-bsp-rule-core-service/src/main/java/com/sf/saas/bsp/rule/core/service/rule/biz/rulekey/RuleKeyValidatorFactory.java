package com.sf.saas.bsp.rule.core.service.rule.biz.rulekey;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 01407460
 * @date 2022/10/28 14:48
 */
@Component
public class RuleKeyValidatorFactory implements InitializingBean {

    @Autowired
    private List<RuleKeyValidator> ruleKeyValidators;

    private Map<String, RuleKeyValidator> factory = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        for (RuleKeyValidator validator : ruleKeyValidators) {
            factory.put(validator.support().name(),validator);
        }
    }

    public RuleKeyValidator dispatch(String ruleKey){
        return factory.get(ruleKey);
    }
}
