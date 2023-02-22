package com.sf.saas.bsp.rule.core.dto.bo.condition.pre;

import lombok.Data;

import java.util.List;

/**
 * 每一个 规则字段key 的 情况
 * 一个字段可能有多个condition
 */
@Data
public class ConditionRuleFieldKeyConfig {

    private String joiner;

    /**
     * conditionAndValue
     */
    private List<ConditionRuleFieldValueConfig> configs;

    private String fieldName;

    /**
     * 将configs转成string（排序后的字符串）
     */
    private String oneFieldMd5;

        /*

    {
      "joiner": "OR",
      "configs": [
        {
          "condition": "IN_CONF",
          "configs": [
            "KR"
          ]
        },
        {
          "condition": "NOT_IN_CONF",
          "configs": [
            "KR"
          ]
        }
      ],
      "fieldName": "senderCountryCode"
    }
         */

}
