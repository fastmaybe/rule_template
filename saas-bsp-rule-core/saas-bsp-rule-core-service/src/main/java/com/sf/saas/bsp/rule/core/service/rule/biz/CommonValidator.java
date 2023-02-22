package com.sf.saas.bsp.rule.core.service.rule.biz;

import com.google.common.collect.Lists;
import com.sf.saas.bsp.rule.core.common.constans.RuleTypeEnum;
import com.sf.saas.bsp.rule.core.service.rule.MultiFieldConf;
import com.sf.saas.bsp.rule.core.service.rule.PostValidator;
import com.sf.saas.bsp.rule.core.service.rule.bo.RuleConfBo;
import com.sf.saas.bsp.rule.core.service.rule.bo.RuleParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author 01407460
 * @date 2022/10/27 17:36
 */
@Slf4j
@Component
public class CommonValidator implements PostValidator {


    @Override
    public Boolean isValidate(RuleParam param) {
        RuleConfBo ruleConfBo = param.getRuleConfBo();
        if (Objects.isNull(ruleConfBo) || Objects.isNull(ruleConfBo.getCheckRule())){
            // 规则为空 直接返回 通过
            return true;
        }
        MultiFieldConf checkRule = ruleConfBo.getCheckRule();
        return checkRule.isValidate(param.getTarget());
    }

    @Override
    public List<Integer> support() {
        return Lists.newArrayList(RuleTypeEnum.BASICS.getType(),RuleTypeEnum.COMPOUND.getType());
    }
}
