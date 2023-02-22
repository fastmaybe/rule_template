package com.sf.saas.bsp.rule.core.common.constans;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

/**
 * @author 01407460
 * @date 2022/9/7 10:28
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {


    //通用成功
    SUCCESS("OK", "操作成功"),
    //通用失败
    FAILURE("FAIL", "操作失败"),

    RULE_NAME_CANNOT_NULL("RULE_NAME_CANNOT_NULL", "规则名称不能为空！"),// The rule name cannot be empty!
    RULE_FIELD_KEY_CANNOT_NULL("RULE_FIELD_KEY_CANNOT_NULL", "规则字段key不能为空！"),// The rule field key cannot be empty!
    RULE_STATE_IS_ERROR("RULE_STATE_IS_ERROR", "规则状态填写不正确！"),// The rule status is incorrect!
    RULE_ORDER_CHANNEL_IS_ERROR("RULE_ORDER_CHANNEL_IS_ERROR", "规则下单渠道填写不正确！"),// The order channel of the rule is not filled correctly!
    RULE_TYPE_IS_ERROR("RULE_TYPE_IS_ERROR", "规则类型填写不正确！"),// The type of the rule is not filled correctly!
    RULE_FIELD_CONDITION_NOT_NULL("RULE_FIELD_CONDITION_NOT_NULL", "规则字段对条件不能为空！"),// Rule field to condition cannot be empty!
    RULE_FIELD_CONDITION_VALUE_IS_ERROR("RULE_FIELD_CONDITION_VALUE_IS_ERROR", "规则字段对应值内容不能为空！"),// Rule field value content cannot be empty!
    AND_OR_IS_ERROR("AND_OR_IS_ERROR", "and/or 选择错误 ！"),// and/or select error!
    BASE_RULE_IS_ERROR("BASE_RULE_IS_ERROR", "基础规则配置错误 ！"),// and/or select error!
    RULE_IS_REPEAT("RULE_IS_REPEAT", "规则重复 ！"),// rule is repeat!
    RULE_JSON_ERROR("RULE_JSON_ERROR", "规则配置错误 ！"),// rule json is error!
    RULE_IS_NOT_EXIST("RULE_IS_NOT_EXIST", "规则不存在！"),// rule is not exist!
    BASE_RULE_CONDITION_IS_ERROR("BASE_RULE_CONDITION_IS_ERROR", "基础规则校验规则一个或多个选择错误 ！"),// One or more basic rule validation rules are selected incorrectly!
    BASE_RULE_CONDITION_IS_ALL_NULL("BASE_RULE_CONDITION_IS_ALL_NULL", "基础规则校验规则至少选择一个 ！"),// Select at least one basic rule verification rule!

    RULE_LANG_IS_EMPTY("RULE_LANG_IS_EMPTY", "规则语言选择为空 ！"),// The rule language selection is empty!
    RULE_LANG_CONTENT_EMPTY("RULE_LANG_CONTENT_EMPTY", "规则语言提示语为空 ！"),// The rule language prompt is empty!
    RULE_LANG_CONTENT_LENGTH_MAX("RULE_LANG_CONTENT_LENGTH_MAX", "规则语言提示语长度不能超过300字符 ！"),// The length of rule language prompt cannot exceed 300 characters!
    RULE_LANG_TYPE_ERROR("RULE_LANG_TYPE_ERROR", "规则语言前后端类型错误 ！"),// The front and back types of the rule language are incorrect!
    RULE_TEMPLATE_TYPE_ERROR("RULE_TEMPLATE_TYPE_ERROR", "规则是否为模板类型错误 ！"),// Whether the rule is template type error!
    RULE_HAS_COPIED_ERROR("RULE_HAS_COPIED_ERROR", "规则已经从模板初始化过，请勿重复操作 ！"),// The rule has been initialized from the template. Do not repeat the operation!



    PARAM_ERROR("PARAM_ERROR", "参数缺失！"),// param error!

    USERNAME_IS_NULL("USERNAME_IS_NULL", "用户名缺失"),
    TENANT_ID_IS_NULL("TENANT_ID_IS_NULL", "租户属性缺失"),
    TENANT_ID_IS_ERROR("TENANT_ID_IS_ERROR", "租户错误"),

    //================= 通用错误码开始



    //================= 某业务域错误码
    STRATEGY_BATCH_OUT_OF_RANGE("STRATEGY_BATCH_OUT_OF_RANGE", "批量数量最大100"),

    //================= 某业务域错误码
    ORDER_CHANNEL_IS_ERROR("ORDER_CHANNEL_IS_ERROR", "下单渠道有误"),


    ;

    /**
     * code 标识 国际化标识
     */
    private String code;


    /**
     * 描述
     */
    private String msg;

    private static final Map<String,ResponseCodeEnum> resCodeSet = new HashMap<>();
    static {
        ResponseCodeEnum[] enums = ResponseCodeEnum.values();
        for (ResponseCodeEnum anEnum : enums) {
            resCodeSet.put(anEnum.getCode(),anEnum);
        }
    }

    public static ResponseCodeEnum getCodeEnum(String code){
        ResponseCodeEnum codeEnum = resCodeSet.get(code);
        if (Objects.isNull(codeEnum)){
            return ResponseCodeEnum.FAILURE;
        }
        return  codeEnum;
    }

}
