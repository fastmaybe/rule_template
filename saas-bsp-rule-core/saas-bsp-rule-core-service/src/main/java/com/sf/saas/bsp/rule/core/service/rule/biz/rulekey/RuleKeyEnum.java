package com.sf.saas.bsp.rule.core.service.rule.biz.rulekey;

import lombok.Getter;

/**
 * @author 01407460
 * @date 2022/10/28 14:43
 */
@SuppressWarnings("all")
@Getter
public enum RuleKeyEnum {

    RULE_KEY_TEST1_TEST("测试ruleKey的简述","测试ruleKey的英文简述");

    private String descZh;
    private String descEn;


    RuleKeyEnum(String descZh,String descEn) {
        this.descZh = descZh;
        this.descEn = descEn;
    }


}
