package com.sf.saas.bsp.rule.core.service.rule.param.valid;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sf.saas.bsp.rule.core.common.constans.ResponseCodeEnum;
import com.sf.saas.bsp.rule.core.common.utils.ValidCodeMsgTuple;

/**
 * Description StrategyDimensionGetValidtoerUtil
 *
 * @author suntao(01408885)
 * @since 2022-09-14
 */
public class RuleConditionValidatorHandler {

    /**
     * 根据运算符 获取 规则条件
     * @param condition
     * @return T:错误信息，成功的时候返回规则条件字符串
     *          D: 规则条件字符串（CONDITION_NULL，SINGLE_STRING，SET）
     */
    public static ValidCodeMsgTuple<ResponseCodeEnum, String> getValidatorInstanceType(String condition) {
        if (StringUtils.isBlank(condition)) {
            return ValidCodeMsgTuple.error(ResponseCodeEnum.RULE_FIELD_CONDITION_NOT_NULL);
        }
        switch (condition) {
            case "REQUIRED" :
                return ValidCodeMsgTuple.success("REQUIRED");
            case "ISNULL" :
            case "NOT_NULL" : return ValidCodeMsgTuple.success("CONDITION_NULL");
            case "EQUALS" :
            case "NOT_EQUALS" :
            case "CONTAINS" :
            case "NOT_CONTAINS" :
            case "STARTS_WITH" :
            case "ENDS_WITH" :
            case "MATCH" :
            case "NOT_MATCH" :
                return ValidCodeMsgTuple.success("SINGLE_STRING");
            case "IN_CONF" :
            case "NOT_IN_CONF" :
                return ValidCodeMsgTuple.success("SET");
            case "LESS" :
            case "GREATER" :
                return ValidCodeMsgTuple.success("SINGLE_DECIMAL");
            case "BETWEEN" :
            case "LENGTH_BETWEEN" :
            case "LENGTH_BETWEEN_WITH_BOTH" :
            case "BETWEEN_WITH_BOTH" :
                return ValidCodeMsgTuple.success("BETWEEN");
            default: return ValidCodeMsgTuple.error(ResponseCodeEnum.RULE_FIELD_CONDITION_NOT_NULL);
        }


    }
}
