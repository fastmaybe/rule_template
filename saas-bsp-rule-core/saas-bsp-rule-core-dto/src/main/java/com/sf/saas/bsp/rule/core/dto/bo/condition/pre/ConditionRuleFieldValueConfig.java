package com.sf.saas.bsp.rule.core.dto.bo.condition.pre;

import lombok.Data;

import java.util.List;

/**
 * 每一个条件的 条件+value
 * 即是 condition+configs
 */
@Data
public class ConditionRuleFieldValueConfig {

    private String condition;

    private Object configs;

    /**
     * 最里层  每一个条件的value 的 md5 (不包含condition)
     */
    private String unitConfigsValueMd5;


 /*

        {
          "condition": "IN_CONF",
          "configs": [
            "KR"
          ]
        }

*/

}
