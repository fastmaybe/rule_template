package com.sf.saas.bsp.rule.core.service.rule.param.valid;

import com.sf.saas.bsp.rule.core.common.constans.ResponseCodeEnum;
import com.sf.saas.bsp.rule.core.common.utils.ValidCodeMsg;
import com.sf.saas.bsp.rule.core.dto.bo.condition.pre.ConditionRuleFieldKeyConfig;
import com.sf.saas.bsp.rule.core.dto.bo.condition.pre.ConditionRuleFieldValueConfig;
import com.sf.saas.bsp.rule.core.dto.bo.condition.pre.ConditionRuleOutJoinerConfig;
import com.sf.saas.bsp.rule.core.service.rule.ConditionEnum;
import com.sf.saas.bsp.rule.core.service.rule.Joiner;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * Description IStrategyDimensionValidator
 *
 * @author suntao(01408885)
 * @since 2022-09-14
 */
@Log4j2
public class RulePreConditionConfigBaseValidator {


    /**
     * @return
     * @param conditionRuleOutJoinerConfig
     */
    public static ValidCodeMsg<ResponseCodeEnum> validConditionBase(ConditionRuleOutJoinerConfig conditionRuleOutJoinerConfig) {
        if (conditionRuleOutJoinerConfig == null) {
            // json 解析结果为空
            log.info("json 解析结果为空");
            return ValidCodeMsg.error(ResponseCodeEnum.RULE_JSON_ERROR);
        }
        if (!Joiner.constainsJoinerStr(conditionRuleOutJoinerConfig.getJoiner())) {
            // 最外层 AND  OR连接 选择错误AND_OR_IS_ERROR
            log.info("最外层 AND/OR连接 选择错误[{}]", conditionRuleOutJoinerConfig.getJoiner());
            return ValidCodeMsg.error(ResponseCodeEnum.AND_OR_IS_ERROR);
        }

        // 每个条件的 详细
        List<ConditionRuleFieldKeyConfig> fieldConfigList = conditionRuleOutJoinerConfig.getConfigs();
        for (ConditionRuleFieldKeyConfig conditionRuleFieldKeyConfig : fieldConfigList) {
            if (!Joiner.constainsJoinerStr(conditionRuleFieldKeyConfig.getJoiner())) {
                // 最外层 AND  OR连接 选择错误AND_OR_IS_ERROR
                log.info("每个pre_key AND/OR连接 选择错误[{}]", conditionRuleFieldKeyConfig.getJoiner());
                return ValidCodeMsg.error(ResponseCodeEnum.AND_OR_IS_ERROR);
            }

            // 每个preKey的值情况
            List<ConditionRuleFieldValueConfig> fieldValueConfigs = conditionRuleFieldKeyConfig.getConfigs();
            for (ConditionRuleFieldValueConfig fieldValueConfig : fieldValueConfigs) {
                if (!ConditionEnum.contains(fieldValueConfig.getCondition())) {
                    log.info("pre_key对应的条件[{}]未在配置列表中", fieldValueConfig.getCondition());
                    return ValidCodeMsg.error(ResponseCodeEnum.RULE_FIELD_CONDITION_NOT_NULL);
                }
            }
        }

        return ValidCodeMsg.success(ResponseCodeEnum.SUCCESS);

    }
}
