package com.sf.saas.bsp.rule.core.service.rule;

import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 01407460
 * @date 2022/10/27 11:22
 */
@Data
public class MultiFieldConf implements Serializable, PreValidator {


    private static final long serialVersionUID = 1L;

    /**
     * 多字段配置
     */
    private List<FieldConf> configs;

    /**
     * 连接符
     */
    private Joiner joiner = Joiner.AND;

    /**
     * md5 标志
     */
    private String md5;

    @Override
    public Boolean isValidate(JSON target) {
        Assert.notEmpty(configs, "配置项为空");
        return joiner.isValidate(configs, target);
    }

}
