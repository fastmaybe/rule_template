package com.sf.saas.bsp.rule.core.dto.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 01407460
 * @date 2022/10/31 14:11
 */
@Data
public class CheckValidResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "rule提示msg")
    private String msg;

    @ApiModelProperty(value = "fieldKey")
    private String fieldKey;

    @ApiModelProperty(value = "name")
    private String ruleName;


    @ApiModelProperty(value = "rule提示msg")
    private String ruleJson;

    public static CheckValidResult build(String ruleName,String msg,String ruleJson,String fieldKey) {
        CheckValidResult result = new CheckValidResult();
        result.setRuleName(ruleName);
        result.setMsg(msg);
        result.setRuleJson(ruleJson);
        result.setFieldKey(fieldKey);
        return result;
    }

}
