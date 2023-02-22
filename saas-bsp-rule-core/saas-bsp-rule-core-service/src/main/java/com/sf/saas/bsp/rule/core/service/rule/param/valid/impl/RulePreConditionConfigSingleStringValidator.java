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
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


/**
 * Description StrategyDimensionStringValidator
 *
 * @author suntao(01408885)
 * @since 2022-09-14
 */
@Log4j2
@Component
public class RulePreConditionConfigSingleStringValidator extends RulePreConditionConfigBaseValidator implements IRuleConditionParamValueValidator {

    static final private String TYPE="SINGLE_STRING";

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

        String value = objects.getString(BspRuleConstant.i_0);

        if (StringUtils.isBlank(value)) {
            log.info("[{}]，configs配置错误,value为空【{}】", TYPE, String.valueOf(configs));
            return ValidCodeMsg.error(ResponseCodeEnum.RULE_FIELD_CONDITION_VALUE_IS_ERROR);
        }
        return ValidCodeMsg.success();
    }

    @Override
    public String getValueMd5(Object configs) {
        JSONArray objects = JSONObject.parseArray(String.valueOf(configs));
        return objects.getString(BspRuleConstant.i_0);
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
