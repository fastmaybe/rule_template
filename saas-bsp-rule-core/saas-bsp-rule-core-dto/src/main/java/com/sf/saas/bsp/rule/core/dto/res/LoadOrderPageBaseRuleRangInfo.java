package com.sf.saas.bsp.rule.core.dto.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Description LogQueryPageVo
 *
 * @author suntao(01408885)
 * @since 2022-11-02
 */
@Data
public class LoadOrderPageBaseRuleRangInfo {

    @ApiModelProperty(value = "区间BETWEEN_WITH_BOTH/大于GREATER/小于LESS")
    private String condition;

    @ApiModelProperty(value = "condition对应的参数")
    private List<Object> configs;



}
