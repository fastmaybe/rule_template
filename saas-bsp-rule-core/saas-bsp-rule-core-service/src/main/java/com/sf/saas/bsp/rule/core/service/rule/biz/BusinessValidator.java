package com.sf.saas.bsp.rule.core.service.rule.biz;

import com.google.common.collect.Lists;
import com.sf.saas.bsp.rule.core.common.constans.RuleTypeEnum;
import com.sf.saas.bsp.rule.core.service.rule.PostValidator;
import com.sf.saas.bsp.rule.core.service.rule.biz.rulekey.RuleKeyValidator;
import com.sf.saas.bsp.rule.core.service.rule.biz.rulekey.RuleKeyValidatorFactory;
import com.sf.saas.bsp.rule.core.service.rule.bo.RuleConfBo;
import com.sf.saas.bsp.rule.core.service.rule.bo.RuleParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author 01407460
 * @date 2022/10/27 17:37
 */
@Slf4j
@Component
public class BusinessValidator implements PostValidator {

    @Autowired
    private RuleKeyValidatorFactory factory;

    @Override
    public Boolean isValidate(RuleParam param) {
        RuleConfBo ruleConfBo = param.getRuleConfBo();

        if (Objects.isNull(ruleConfBo) || StringUtils.isBlank(ruleConfBo.getRuleKey())){
            //不存在ruleKey  直接通过
            return true;
        }

        RuleKeyValidator validator = factory.dispatch(ruleConfBo.getRuleKey());
        if (Objects.isNull(validator)){
            //不存在validator  直接通过
            return true;
        }

        return validator.isValidate(param.getTarget());
    }

    @Override
    public List<Integer> support() {
        return Lists.newArrayList(RuleTypeEnum.SPECIAL.getType());
    }
}
