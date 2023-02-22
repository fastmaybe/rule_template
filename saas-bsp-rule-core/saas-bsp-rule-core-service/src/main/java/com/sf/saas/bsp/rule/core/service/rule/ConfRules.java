package com.sf.saas.bsp.rule.core.service.rule;

import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 01407460
 * @date 2022/10/27 14:33
 */
@Data
public class ConfRules implements Serializable, PreValidator {



    /**
     *
     */
    private List<MultiFieldConf> configs;

    /**
     * 连接符
     */
    private Joiner joiner = Joiner.AND;

    @Override
    public Boolean isValidate(JSON target) {
        Assert.notEmpty(configs, "配置项为空");
        return joiner.isValidate(configs, target);
    }
}
