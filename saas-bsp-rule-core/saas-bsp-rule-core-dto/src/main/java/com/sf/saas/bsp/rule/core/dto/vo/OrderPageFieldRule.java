package com.sf.saas.bsp.rule.core.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Description 下单域拉取基础规则
 *
 * @author suntao(01408885)
 * @since 2022-10-26
 */
@Data
public class OrderPageFieldRule {


    /**
     * 租户
     */
    @ApiModelProperty(value = "租户", required = true)
    private String tenantId;

    /**
     * 配置名称
     */
    @ApiModelProperty(value = "规则名称", required = true)
    private String configName;

    /**
     * 字段key
     */
    @ApiModelProperty(value = "规则字段key", required = true)
    private String fieldKey;

    /**
     * 是否生效：0-否；1-是
     */
    @ApiModelProperty(value = "是否生效：0-否；1-是", required = true)
    @NotNull(message = "RULE_STATE_IS_ERROR")
    @Range(min = 0, max = 1, message = "RULE_STATE_IS_ERROR")
    private Integer state;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 下单渠道：1-WEB单票；2-WEB导入；3-jvgo；4-api单个；5-api批量，多个逗号分隔
     */
    @ApiModelProperty(value = "下单渠道：1-WEB单票；2-WEB导入；3-jvgo；4-api单个；5-api批量，多个逗号分隔", required = true)
    @NotNull(message = "RULE_ORDER_CHANNEL_IS_ERROR")
    private String orderChannel;

    /**
     * 规则类型
     */
    @ApiModelProperty(value = "规则类型: 1-基础规则；2-复合规则；3-特殊规则", required = true)
    @Range(min = 1, max = 3, message = "RULE_TYPE_IS_ERROR")
    @NotNull(message = "RULE_TYPE_IS_ERROR")
    private Integer ruleType;

    /**
     * 特殊配置key值
     */
    @ApiModelProperty(value = "特殊配置key值")
    private String configKey;

    /**
     * 规则条件
     */
    @ApiModelProperty(value = "规则json，前端以字符串给到后端", required = true)
    @NotBlank(message = "RULE_FIELD_CONDITION_NOT_NULL")
    private String ruleCondition;

    /**
     * 基础规则 joiner表示
     */
    @ApiModelProperty(value = "基础规则 joiner表示, 前端不用管", required = false)
    private String ruleConditionBackstage;



    /**
     * 月结卡号白名单，逗号分隔
     */
    @ApiModelProperty(value = "月结卡号白名单，逗号分隔")
    private String monthCardNoWhite;


}
