package com.sf.saas.bsp.rule.core.common.emnus;

import org.apache.commons.lang3.StringUtils;

/**
 * Description BaseRuleFieldNameEnum
 *
 * @author suntao(01408885)
 * @since 2022-11-10
 */
public enum BaseRuleFieldNameEnum {
    REQUIRED("required","必填字段"),
    FIELD_LENGTH("fieldLength", "字段长度"),
    VALUE_RANGE("valueRange", "值区间"),
    REGEX("regex", "正则");




    private String fieldName;
    private String desc;

    BaseRuleFieldNameEnum(String fieldName, String desc) {
        this.fieldName = fieldName;
        this.desc = desc;
    }

    public String getFieldName() {
        return fieldName;
    }

    public static BaseRuleFieldNameEnum getByFieldName(String fieldName) {
        if (StringUtils.isBlank(fieldName)) {
            return null;
        }
        for (BaseRuleFieldNameEnum fieldNameEnum : BaseRuleFieldNameEnum.values()) {
            if (fieldNameEnum.getFieldName().equals(fieldName)) {
                return fieldNameEnum;
            }
        }
        return null;
    }
}
