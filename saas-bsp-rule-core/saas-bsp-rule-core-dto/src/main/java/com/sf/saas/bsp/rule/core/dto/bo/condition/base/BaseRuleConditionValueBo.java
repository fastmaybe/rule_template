package com.sf.saas.bsp.rule.core.dto.bo.condition.base;

import lombok.Data;
import org.apache.commons.lang3.BooleanUtils;

/**
 * 基础规则解析
 */
@Data
public class BaseRuleConditionValueBo {

    private String condition;

    private String inputKey;

    private Object configs;

    /**
     * 将configs转成string（排序后的字符串）
     */
    private String configsString;

    /**
     * 这项是否为空
     * true 该项为空
     * null 不能为空
     * false 不能为空
     */
    private Boolean isBlank;


    /**
     * 不为空
     * @return
     */
    public boolean confIsNotBlank() {
        return BooleanUtils.isNotTrue(isBlank);
    }

    /**
     * 为空
     * @return
     */
    public boolean confIsBlank() {
        return BooleanUtils.isTrue(isBlank);
    }


 /*

[
    {
      "configs": [
          true
      ],
      "condition": "REQUIRED",
      "inputKey": "required"
    },
    {
      "configs": [
        1,100
      ],
      "condition": "BETWEEN",
      "inputKey": "fieldLenth"
    },
    {
      "configs": [
        100
      ],
      "condition": "LESS/GREATER/BETWEEN",
      "inputKey": "valueRange"
    },
    {
      "configs": [
        "^$d[0-2]^"
      ],
      "condition": "MATCH",
      "inputKey": "regex"
    }
]

*/

}
