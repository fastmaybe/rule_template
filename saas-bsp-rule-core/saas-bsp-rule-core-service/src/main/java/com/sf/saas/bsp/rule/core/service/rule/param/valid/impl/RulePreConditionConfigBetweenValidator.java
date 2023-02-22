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
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;


/**
 * Description StrategyDimensionStringValidator
 *
 * @author suntao(01408885)
 * @since 2022-09-14
 */
@Log4j2
@Component
public class RulePreConditionConfigBetweenValidator extends RulePreConditionConfigBaseValidator implements IRuleConditionParamValueValidator {

    static final private String TYPE="BETWEEN";

    @Override
    public ValidCodeMsg<ResponseCodeEnum> validate(Object configs) {

        if (configs == null) {
            log.info("[{}]，configs为空", TYPE);
            return ValidCodeMsg.error(ResponseCodeEnum.RULE_FIELD_CONDITION_VALUE_IS_ERROR);
        }
        JSONArray objects = JSONObject.parseArray(String.valueOf(configs));
        if (objects.size() != BspRuleConstant.i_2) {
            log.info("[{}]，配置错误【{}】", TYPE, String.valueOf(configs));
            return ValidCodeMsg.error(ResponseCodeEnum.RULE_FIELD_CONDITION_VALUE_IS_ERROR);
        }
        double p1;
        double p2;


        try {
            p1 = objects.getDouble(BspRuleConstant.i_0);
            p2 = objects.getDouble(BspRuleConstant.i_1);
        } catch (NumberFormatException e) {
            log.info("数值类型转换错误e={}", ExceptionUtils.getStackTrace(e));
            return ValidCodeMsg.error(ResponseCodeEnum.RULE_FIELD_CONDITION_VALUE_IS_ERROR);
        } catch (Exception e) {
            log.info("数值类型转换错误e={}", ExceptionUtils.getStackTrace(e));
            return ValidCodeMsg.error(ResponseCodeEnum.RULE_FIELD_CONDITION_VALUE_IS_ERROR);
        }

        if (p2 < p1) {
            log.info("[{}]，configs配置错误,前者大于了后者【{}】", TYPE, String.valueOf(configs));
            return ValidCodeMsg.error(ResponseCodeEnum.RULE_FIELD_CONDITION_VALUE_IS_ERROR);
        }
        return ValidCodeMsg.success();
    }

    @Override
    public String getValueMd5(Object configs) {
        JSONArray objects = JSONObject.parseArray(String.valueOf(configs));
        if (objects.size() != BspRuleConstant.i_2) {
            log.info("[{}]，配置错误【{}】", TYPE, String.valueOf(configs));
            return "";
        }

        return StringUtils.join(objects.getString(BspRuleConstant.i_0), objects.getString(BspRuleConstant.i_1));
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
