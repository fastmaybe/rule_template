package com.sf.saas.bsp.rule.core.service.rule.bo;

import cn.hutool.json.JSON;
import lombok.Builder;
import lombok.Data;

/**
 * @author 01407460
 * @date 2022/10/26 15:48
 */
@Data
@Builder
public class RuleParam {

    private RuleConfBo ruleConfBo;

    private JSON target;
}
