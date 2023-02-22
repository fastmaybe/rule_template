package com.sf.saas.bsp.rule.core.service.utils;

import com.google.common.collect.Lists;
import com.sf.saas.bsp.rule.core.dto.bo.condition.base.BaseRuleConditionValueBo;
import com.sf.saas.bsp.rule.core.dto.bo.condition.pre.ConditionRuleFieldKeyConfig;
import com.sf.saas.bsp.rule.core.dto.bo.condition.pre.ConditionRuleFieldValueConfig;
import com.sf.saas.bsp.rule.core.dto.bo.condition.pre.ConditionRuleOutJoinerConfig;
import com.sf.saas.bsp.rule.core.service.rule.Joiner;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Log4j2
public class ConditionUtil {

    /**
     * 计算 前置规则的md5值 用于 比较两个前置规则是否相同
     * @param conditionRuleOutJoinerConfig
     * @return
     */
    public static String getPreConditionMd5(ConditionRuleOutJoinerConfig conditionRuleOutJoinerConfig) {
        // 最外层（所有字段+所有字段连接条件）：ConditionRuleOutJoinerConfig： configs 所有字段的情况
        // 字段配置层（所有条件+条件连接）：ConditionRuleFieldKeyConfig：configs 条件和值
        // 最内层 ConditionRuleFieldValueConfig config：每一个条件的 value

        // 所有字段排序map, map<字段名, 字段的md5>
        TreeMap<String, String> allFieldAndValueMap = new TreeMap<String, String>();

        for (ConditionRuleFieldKeyConfig fieldKeyConfig : conditionRuleOutJoinerConfig.getConfigs()) {

            // 一个字段的多个condition, map<condition, value的md5>
            TreeMap<String, String> oneFieldMutCondition = new TreeMap<String, String>();
            List<ConditionRuleFieldValueConfig> configs = fieldKeyConfig.getConfigs();
            for (ConditionRuleFieldValueConfig valueConditionConfig : configs) {
                oneFieldMutCondition.put(valueConditionConfig.getCondition(), valueConditionConfig.getUnitConfigsValueMd5());
            }
            // 一个字段的多个condition  的md5
            String oneFieldMutValueMd5 = MD5Util.getMd5ValueFromTreeMap(oneFieldMutCondition);

            // 字段的md5（包含所有条件的md5+条件连接方式）
            fieldKeyConfig.setOneFieldMd5(oneFieldMutValueMd5 + fieldKeyConfig.getJoiner());


            // allFieldAndValueMap 赋值
            allFieldAndValueMap.put(fieldKeyConfig.getFieldName(), fieldKeyConfig.getOneFieldMd5());
        }
        // 前置条件 整体md5
        conditionRuleOutJoinerConfig.setRuleMd5(MD5Util.getMd5ValueFromTreeMap(allFieldAndValueMap));

        return conditionRuleOutJoinerConfig.getRuleMd5();
    }


    /**
     * 将基础条件转成前置条件类似json 存储到数据库
     *
     * @param fieldKey
     * @param baseRuleConditionValueBos
     * @return
     */
    public static ConditionRuleOutJoinerConfig convertRuleCondition2PreCondition(String fieldKey, List<BaseRuleConditionValueBo> baseRuleConditionValueBos) {


        // field
        ConditionRuleFieldKeyConfig conditionRuleFieldKeyConfig = new ConditionRuleFieldKeyConfig();
        conditionRuleFieldKeyConfig.setJoiner(Joiner.AND.name());
        conditionRuleFieldKeyConfig.setFieldName(fieldKey);

        List<ConditionRuleFieldValueConfig> ruleFieldValueConfigs = baseRuleConditionValueBos.stream().filter(BaseRuleConditionValueBo::confIsNotBlank).map(brc -> {
            ConditionRuleFieldValueConfig conditionRuleFieldValueConfig = new ConditionRuleFieldValueConfig();
            conditionRuleFieldValueConfig.setCondition(brc.getCondition());
            conditionRuleFieldValueConfig.setConfigs(brc.getConfigs());
            return conditionRuleFieldValueConfig;
        }).collect(Collectors.toList());

        conditionRuleFieldKeyConfig.setConfigs(ruleFieldValueConfigs);

        // out
        ConditionRuleOutJoinerConfig conditionRuleOutJoinerConfig = new ConditionRuleOutJoinerConfig();
        conditionRuleOutJoinerConfig.setJoiner(Joiner.AND.name());
        conditionRuleOutJoinerConfig.setConfigs(Lists.newArrayList(conditionRuleFieldKeyConfig));

        return conditionRuleOutJoinerConfig;
    }
}


/*
前置规则json样式
{
  "joiner": "AND",
  "configs": [
    {
      "joiner": "OR",
      "configs": [
        {
          "condition": "IN_CONF",
          "configs": [
            "KR"
          ]
        }
      ],
      "fieldName": "senderCountryCode"
    },
    {
      "joiner": "OR",
      "configs": [
        {
          "condition": "NOT_IN_CONF",
          "configs": [
            "C93801",
            "C93802",
            "C93803",
            "C93804",
            "C933"
          ]
        }
      ],
      "fieldName": "cargoTypeCode"
    }
  ],
  "empty": false
}

 */