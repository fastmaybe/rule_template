package com.sf.saas.bsp.rule.core.service.rule;

import lombok.Data;

import java.util.List;

/**
 * @author 01407460
 * @date 2022/10/26 15:56
 */
@Data
public class LineConf {

    private static final long serialVersionUID = 1L;

    /**
     * 条件表达式
     */
    private ConditionEnum condition;

    /**
     * 多行配置
     */
    private List<String> configs;

    /**
     * null 时候 flag
     */
    private boolean flagWhenNull;
}
