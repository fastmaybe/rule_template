package com.sf.saas.bsp.rule.core.dto.req;

import com.sf.saas.bsp.rule.core.dto.base.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * Description StrategyVoQueryReq
 *
 * @author suntao(01408885)
 * @since 2022-09-08
 */
@Data
public class RuleVoQueryReq extends PageParam {

    /**
     * 策略名
     */
    @ApiModelProperty(value = "规则名称")
    private String configName;

    /**
     * 租户
     */
    @ApiModelProperty(value = "租户")
    private String tenantId;

    /**
     * 策略类型 reject  charge  prompt
     */
    @ApiModelProperty(value = "规则类型: 1-基础规则；2-复合规则；3-特殊规则")
    @Range(min = 1, max = 3, message = "RULE_TYPE_IS_ERROR")
    private Integer ruleType;


    /**
     * 规则字段
     */
    @ApiModelProperty(value = "规则字段")
    private String fieldKey;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @Range(min = 0, max = 1, message = "RULE_STATE_IS_ERROR")
    private Integer state;

    /**
     * 语言
     */
    private String lang;

    @ApiModelProperty(value = "模板规则 0:非模板 1:模板规则", required = true)
    @Range(min = 0, max = 1, message = "RULE_TEMPLATE_TYPE_ERROR")
    @NotNull(message = "RULE_TEMPLATE_TYPE_ERROR")
    private Integer ruleTemplate;
}
