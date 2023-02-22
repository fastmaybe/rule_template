package com.sf.saas.bsp.rule.core.service.rule.bo;

import com.sf.saas.bsp.rule.core.service.rule.ConfRules;
import com.sf.saas.bsp.rule.core.service.rule.MultiFieldConf;
import lombok.Data;

import java.util.Map;

/**
 * @author 01407460
 * @date 2022/10/26 15:49
 */
@Data
public class RuleConfBo {

    /**
     * 规则类型
     */
    private Integer type;

    private String ruleName;
    /**
     * 规则ruleKey
     */
    private String ruleKey;

    /**
     * 不通过  提示code 目前默认为  字段path  // 弃用
     */
    private String msgCode;

    private String fieldKey;

    /**
     * 白名单配置  OR关系
     */
    private MultiFieldConf skipCond;

    /**
     * 前置规则校验
     */
    private ConfRules preCond;

    /**
     *后置规则校验
     */
    private MultiFieldConf checkRule;


    private String checkRuleJson;

    /**
     * 语言提示
     */
    private Map<String,String> tipMap;

}
