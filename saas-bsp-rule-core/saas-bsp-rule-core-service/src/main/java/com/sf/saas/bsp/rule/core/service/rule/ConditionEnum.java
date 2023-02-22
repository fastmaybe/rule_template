package com.sf.saas.bsp.rule.core.service.rule;


import com.sf.saas.bsp.rule.core.service.rule.validators.*;
import com.sf.saas.bsp.rule.core.service.rule.validators.comparable.*;
import com.sf.saas.bsp.rule.core.service.rule.validators.string.*;
import lombok.Getter;

/**
 * @author 01407460
 * @date 2022/10/26 15:57
 */
@Getter
public enum ConditionEnum {

    // ********** 基础条件 begin *****************

    ISNULL("isNull", "isNull", "为null", 0, Object.class, new IsNull(new IsBlank())),

    NOT_NULL("notNull", "notNull", "非null", 0, Object.class, new IsNotNull(new IsNotBlank())),

//    IS_TRUE("isTrue", "isTrue", "为真", 0, Boolean.class, new IsTrue()),

//    IS_FALSE("isFalse", "isFalse", "为假", 0, Boolean.class, new IsFalse()),

    EQUALS("==", "eq", "等于", 1, Object.class, new Equals()),

    NOT_EQUALS("!=", "neq", "不等于", 1, Object.class, new NotEquals()),

    IN_CONF("in", "in", "在集合内", -1, Object.class, new InConf()),

    NOT_IN_CONF("notIn", "notIn", "不在集合内", -1, Object.class, new NotInConf()),

    REQUIRED("required", "required", "是否必填", 1, Object.class, new RequiredConf(new IsNotNull(new IsNotBlank()))),

    // ********** 比较条件 begin ***************

    LESS("<", "ls", "小于", 1, Comparable.class, new Less()),

    LESS_EQUALS("<=", "le", "小于等于", 1, Comparable.class, new LessEquals()),

    GREATER(">", "gt", "大于", 1, Comparable.class, new Greater()),

    GREATER_EQUALS(">=", "ge", "大于等于", 1, Comparable.class, new GreaterEquals()),

    BETWEEN("()", "bw", "区间", 2, Comparable.class, new Between()),

//    BETWEEN_WITH_LEFT("[)", "bwl", "区间-左", 2, Comparable.class, new BetweenIncludeLeft()),

//    BETWEEN_WITH_RIGHT("(]", "bwr", "区间-右", 2, Comparable.class, new BetweenIncludeRight()),

    BETWEEN_WITH_BOTH("[]", "bwlr", "区间-左右", 2, Comparable.class, new BetweenIncludeBoth()),


    // ********** 字符串条件 begin ***************

//    IS_BLANK("isBlank", "isBlank", "为空", 0, String.class, new IsBlank()),

//    IS_NOT_BLANK("isNotBlank", "isNotBlank", "非空", 0, String.class, new IsNotBlank()),

    STARTS_WITH("startsWith", "startsWith", "前缀", 1, String.class, new StartsWith()),

    ENDS_WITH("endsWith", "endsWith", "后缀", 1, String.class, new EndsWith()),

    CONTAINS("contains", "contains", "包含", 1, String.class, new Contains()),

    NOT_CONTAINS("notContains", "notContains", "不包含", 1, String.class, new NotContains()),

    MATCH("match", "match", "匹配", 1, String.class, new Match()),

    NOT_MATCH("notMatch", "notMatch", "不匹配", 1, String.class, new NotMatch()),

//    LENGTH_EQUALS("lengthEq", "lengthEq", "长度等于", 1, String.class, new FieldValidatorAdapter<String>(new Equals(), FieldValidatorAdapter::stringLength)),

//    LENGTH_NOT_EQUALS("lengthNeq", "lengthNeq", "长度不等于", 1, String.class, new FieldValidatorAdapter<String>(new NotEquals(), FieldValidatorAdapter::stringLength)),

    LENGTH_LESS("lengthLs", "lengthLess", "长度小于", 1, String.class, new FieldValidatorAdapter<String>(new Less(), FieldValidatorAdapter::stringLength)),

    LENGTH_LESS_EQUALS("lengthLe", "lengthLessEquals", "长度小于等于", 1, String.class, new FieldValidatorAdapter<String>(new LessEquals(), FieldValidatorAdapter::stringLength)),

    LENGTH_GREATER("lengthGt", "lengthGreater", "长度大于", 1, String.class, new FieldValidatorAdapter<String>(new Greater(), FieldValidatorAdapter::stringLength)),

    LENGTH_GREATER_EQUALS("lengthGe", "lengthGreaterEquals", "长度大于等于", 1, String.class, new FieldValidatorAdapter<String>(new GreaterEquals(), FieldValidatorAdapter::stringLength)),

    LENGTH_BETWEEN("lengthBw", "lengthBetween", "长度区间", 2, String.class, new FieldValidatorAdapter<String>(new Between(), FieldValidatorAdapter::stringLength)),

    LENGTH_BETWEEN_WITH_BOTH("lengthBwlr", "lengthBetweenWithlr", "长度区间", 2, String.class, new FieldValidatorAdapter<String>(new BetweenIncludeBoth(), FieldValidatorAdapter::stringLength)),

    // ********** 字符串条件 end ***************
    ;

    private String origin;
    private String label;
    private String note;

    /**
     * 需要的配置数
     * -1 代表配置需要是 list
     */
    private int configSize;
    private Class<?> supportType;
    private FieldValidator validator;

    ConditionEnum(String origin, String label, String note, int configSize, Class<?> supportType, FieldValidator validator) {
        this.origin = origin;
        this.label = label;
        this.note = note;
        this.configSize = configSize;
        this.supportType = supportType;
        this.validator = validator;
    }


    public static boolean contains(String conditionName) {
        for (ConditionEnum enu : ConditionEnum.values()) {
            if (enu.name().equals(conditionName)) {
                return true;
            }
        }
        return false;
    }
}
