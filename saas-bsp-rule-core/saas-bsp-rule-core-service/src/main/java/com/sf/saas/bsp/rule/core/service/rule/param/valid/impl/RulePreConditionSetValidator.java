package com.sf.saas.bsp.rule.core.service.rule.param.valid.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sf.saas.bsp.rule.core.common.constans.BspRuleConstant;
import com.sf.saas.bsp.rule.core.common.constans.ResponseCodeEnum;
import com.sf.saas.bsp.rule.core.common.utils.ValidCodeMsg;
import com.sf.saas.bsp.rule.core.dto.bo.condition.pre.ConditionRuleFieldValueConfig;
import com.sf.saas.bsp.rule.core.service.rule.param.valid.IRuleConditionParamValueValidator;
import com.sf.saas.bsp.rule.core.service.rule.param.valid.RulePreConditionConfigBaseValidator;
import com.sf.saas.bsp.rule.core.service.utils.MD5Util;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;


/**
 * Description StrategyDimensionStringValidator
 *
 * @author suntao(01408885)
 * @since 2022-09-14
 */
@Log4j2
@Component
public class RulePreConditionSetValidator extends RulePreConditionConfigBaseValidator implements IRuleConditionParamValueValidator {

    static final private String TYPE="SET";

    @Override
    public ValidCodeMsg<ResponseCodeEnum> validate(Object configs) {


        if (configs == null) {
            log.info("[{}]，configs为空", TYPE);
            return ValidCodeMsg.error(ResponseCodeEnum.RULE_FIELD_CONDITION_VALUE_IS_ERROR);
        }
        JSONArray objects = JSONObject.parseArray(String.valueOf(configs));
        if (objects.size() == BspRuleConstant.i_0) {
            log.info("[{}]，配置错误,集合中没有选择有效值【{}】", TYPE, String.valueOf(configs));
            return ValidCodeMsg.error(ResponseCodeEnum.RULE_FIELD_CONDITION_VALUE_IS_ERROR);
        }

        return ValidCodeMsg.success();
    }

    @Override
    public String getValueMd5(Object configs) {
        JSONArray objects = JSONObject.parseArray(String.valueOf(configs));

        List<String> list = new ArrayList<>();

        for (int i = 0; i < objects.size(); i++) {
            list.add(objects.getString(i));
        }
        Collections.sort(list);

        return MD5Util.md5(StringUtils.join(list));
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
