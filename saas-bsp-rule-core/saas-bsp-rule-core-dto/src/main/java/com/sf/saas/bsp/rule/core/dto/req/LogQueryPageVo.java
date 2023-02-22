package com.sf.saas.bsp.rule.core.dto.req;

import com.sf.saas.bsp.rule.core.dto.base.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Description LogQueryPageVo
 *
 * @author suntao(01408885)
 * @since 2022-11-02
 */
@Data
public class LogQueryPageVo extends PageParam {
    /**
     * 规则主键id
     */
    @ApiModelProperty(value = "规则主键id")
    @NotNull(message = "PARAM_ERROR")
    private Integer ruleId;
}
