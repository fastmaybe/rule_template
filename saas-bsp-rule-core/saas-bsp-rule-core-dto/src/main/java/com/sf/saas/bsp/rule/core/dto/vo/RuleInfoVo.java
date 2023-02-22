package com.sf.saas.bsp.rule.core.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description RuleInfoVo
 *
 * @author suntao(01408885)
 * @since 2022-10-26
 */
@Data
public class RuleInfoVo {


    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;

    /**
     * 租户
     */
    @ApiModelProperty(value = "租户", required = true)
    private String tenantId;

    /**
     * 配置名称
     */
    @ApiModelProperty(value = "规则名称", required = true)
    @NotBlank(message = "RULE_NAME_CANNOT_NULL")
    @Length(max = 100, message = "RULE_NAME_CANNOT_NULL")
    private String configName;

    /**
     * 字段key
     */
    @ApiModelProperty(value = "规则字段key", required = true)
    private String fieldKey;

    /**
     * 字段key
     */
    @ApiModelProperty(value = "前端字段名称", required = true)
    private String webFieldName;

    /**
     * 是否生效：0-否；1-是
     */
    @ApiModelProperty(value = "是否生效：0-否；1-是", required = true)
    private Integer state;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 下单渠道：1-WEB单票；2-WEB导入；3-jvgo；4-api单个；5-api批量，多个逗号分隔
     */
    @ApiModelProperty(value = "下单渠道：1-WEB单票；2-WEB导入；3-jvgo；4-api单个；5-api批量，多个逗号分隔，字典中取值", required = true)
    @NotNull(message = "RULE_ORDER_CHANNEL_IS_ERROR")
    private String orderChannel;

    /**
     * 规则类型
     */
    @ApiModelProperty(value = "规则类型: 1-基础规则；2-复合规则；3-特殊规则，字典中取值", required = true)
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
     * 前置条件
     */
    @ApiModelProperty(value = "前置条件，json，前端以字符串给到后端", required = true)
    private String preCondition;

    /**
     * 前置条件MD5值
     */
    @ApiModelProperty(value = "前置条件MD5值, 前端不用管")
    private String preConditionMd5;

    /**
     * 月结卡号白名单，逗号分隔
     */
    @ApiModelProperty(value = "月结卡号白名单，逗号分隔")
    private String monthCardNoWhite;

    /**
     * 模板规则 0:非模板 1:模板规则
     */
    @ApiModelProperty(value = "模板规则 0:非模板 1:模板规则", required = true)
    @NotNull(message = "RULE_TEMPLATE_TYPE_ERROR")
    @Range(min = 0, max = 1, message = "RULE_TEMPLATE_TYPE_ERROR")
    private Integer ruleTemplate;

    /**
     * 是否来自模板规则 0:不是 1:是
     */
    @ApiModelProperty(value = "是否来自模板规则 0:不是 1:是", required = false)
    private Integer fromTemplate;

    /**
     * 提示语
     */
    @ApiModelProperty(value = "提示语")
    @Valid
    private List<RuleTipContentVo> ruleTipContentVos;

    /**
     * 前端排序
     */
    @ApiModelProperty(value = "前端排序，后期功能")
    private Integer webDisplayOrder;


    /**
     * 前端是否展示
     */
    @ApiModelProperty(value = "前端是否展示，后期功能")
    private Boolean webDisplay;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Long gmtCreate;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Long gmtModified;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String modifiedBy;



}
