package com.sf.saas.bsp.rule.core.service.rule.param.valid.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sf.saas.bsp.rule.core.common.constans.BspRuleConstant;
import com.sf.saas.bsp.rule.core.common.constans.ResponseCodeEnum;
import com.sf.saas.bsp.rule.core.common.utils.ValidCodeMsg;
import com.sf.saas.bsp.rule.core.dto.bo.condition.pre.ConditionRuleFieldValueConfig;
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
public class RulePreConditionConfigRequiredValidator extends RulePreConditionConfigBaseValidator implements IRuleConditionParamValueValidator {

    static final private String TYPE="REQUIRED";

    @Override
    public ValidCodeMsg<ResponseCodeEnum> validate(Object configs) {

        if (configs == null) {
            log.info("[{}]，configs为空", TYPE);
            return ValidCodeMsg.error(ResponseCodeEnum.RULE_FIELD_CONDITION_VALUE_IS_ERROR);
        }
        JSONArray objects = JSONObject.parseArray(String.valueOf(configs));
        if (objects.size() != BspRuleConstant.i_1) {
            log.info("[{}]，配置错误【{}】", TYPE, String.valueOf(configs));
            return ValidCodeMsg.error(ResponseCodeEnum.RULE_FIELD_CONDITION_VALUE_IS_ERROR);
        }

        boolean value = false;
        try {
            value = objects.getBooleanValue(BspRuleConstant.i_0);
        } catch (Exception e) {
            log.info("[{}]，configs配置错误,value转boolean异常【{}】", TYPE, String.valueOf(configs));
            return ValidCodeMsg.error(ResponseCodeEnum.RULE_FIELD_CONDITION_VALUE_IS_ERROR);
        }
        return ValidCodeMsg.success();
    }

    @Override
    public String getValueMd5(Object configs) {
        JSONArray objects = JSONObject.parseArray(String.valueOf(configs));
        if (objects.size() != BspRuleConstant.i_1) {
            log.info("[{}]，配置错误【{}】", TYPE, String.valueOf(configs));
            return "";
        }

        return objects.getString(BspRuleConstant.i_0);
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
