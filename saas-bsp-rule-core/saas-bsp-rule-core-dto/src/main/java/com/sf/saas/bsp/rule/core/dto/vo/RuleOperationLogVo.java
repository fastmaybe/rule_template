package com.sf.saas.bsp.rule.core.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description RuleOperationLogVo
 *
 * @author suntao(01408885)
 * @since 2022-11-02
 */
@Data
public class RuleOperationLogVo {

    @ApiModelProperty(value = "修改人")
    private String user;

    @ApiModelProperty(value = "租户")
    private String tenantId;

    @ApiModelProperty(value = "规则id")
    private Long ruleId;

    @ApiModelProperty(value = "修改时间")
    private Long gmtModified;

    @ApiModelProperty(value = "类型,字典中取operationType")
    private String operationType;

    @ApiModelProperty(value = "改修数据记录")
    private String requestData;
}
