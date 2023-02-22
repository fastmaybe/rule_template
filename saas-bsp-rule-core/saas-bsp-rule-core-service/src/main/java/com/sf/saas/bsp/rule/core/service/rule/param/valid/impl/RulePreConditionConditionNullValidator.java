package com.sf.saas.bsp.rule.core.service.rule.param.valid.impl;

import com.sf.saas.bsp.rule.core.common.constans.ResponseCodeEnum;
import com.sf.saas.bsp.rule.core.common.utils.ValidCodeMsg;
import com.sf.saas.bsp.rule.core.service.rule.param.valid.IRuleConditionParamValueValidator;
import com.sf.saas.bsp.rule.core.service.rule.param.valid.RulePreConditionConfigBaseValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;


/**
 * Description StrategyDimensionStringValidator
 *
 * @author suntao(01408885)
 * @since 2022-09-14
 */
@Log4j2
@Component
public class RulePreConditionConditionNullValidator extends RulePreConditionConfigBaseValidator implements IRuleConditionParamValueValidator {

    static final private String TYPE="CONDITION_NULL";

    @Override
    public ValidCodeMsg<ResponseCodeEnum> validate(Object configs) {

        return ValidCodeMsg.success();
    }

    @Override
    public String getValueMd5(Object configs) {
        return "";
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
