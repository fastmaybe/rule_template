package com.sf.saas.bsp.rule.core.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description DictionarySelectVo
 *
 * @author suntao(01408885)
 * @since 2022-09-07
 */
@Data
public class FieldConditionSelectInfoVo {

    private static final long serialVersionUID = -4908651535967246713L;


    /**
     * key
     */
    @ApiModelProperty(value = "规则字段key")
    private String fieldKey;

    /**
     * 条件
     */
    @ApiModelProperty(value = "规则可以选的条件")
    private String condition;

    /**
     * 参数输入框个数
     */
    @ApiModelProperty(value = "规则条件参数个数")
    private Integer configSize;
}
