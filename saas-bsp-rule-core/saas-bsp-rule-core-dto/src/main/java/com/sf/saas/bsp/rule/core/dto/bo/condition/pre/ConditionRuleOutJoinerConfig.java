package com.sf.saas.bsp.rule.core.dto.bo.condition.pre;

import lombok.Data;

import java.util.List;

/**
 * 校验规则有效性
 * 计算规则md5
 */
@Data
public class ConditionRuleOutJoinerConfig {

    private String joiner;

    /**
     * 字段和他的配置情况
     * fieldKeyConfigs
     */
    private List<ConditionRuleFieldKeyConfig> configs;

    /**
     * 将configs转成string（排序后的字符串）
     */
    private String ruleMd5;


        /*
{
  "joiner": "AND",  // 这里
  "configs": [   // 这里
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

}
