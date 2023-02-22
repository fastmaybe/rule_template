package com.sf.saas.bsp.rule.core.manager.convert;

import com.sf.saas.bsp.rule.core.dao.entity.RuleFieldCondition;
import com.sf.saas.bsp.rule.core.dto.vo.FieldConditionSelectInfoVo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description RuleConditionConvert
 *
 * @author suntao(01408885)
 * @since 2022-10-28
 */
public class RuleConditionConvert {

    public static List<FieldConditionSelectInfoVo> convertDo2VoList(List<RuleFieldCondition> conditions) {
        return conditions.stream().map(RuleConditionConvert::convertDo2Vo).collect(Collectors.toList());
    }

    public static FieldConditionSelectInfoVo convertDo2Vo(RuleFieldCondition fieldCondition) {
        if (fieldCondition == null) {
            return null;
        }
        FieldConditionSelectInfoVo vo = new FieldConditionSelectInfoVo();
        vo.setFieldKey(fieldCondition.getFieldKey());
        vo.setCondition(fieldCondition.getConditionName());
        vo.setConfigSize(fieldCondition.getConfigSize());


        return vo;
    }

}
