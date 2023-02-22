package com.sf.saas.bsp.rule.core.dto.req;

import com.sf.saas.bsp.rule.core.dto.base.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * Description LogQueryPageVo
 *
 * @author suntao(01408885)
 * @since 2022-11-02
 */
@Data
public class LoadOrderPageBaseRuleQueryVo {
    /**
     * tenantId
     */
    @ApiModelProperty(value = "tenantId（header中必须有租户）")
    private String tenantId;

    /**
     * 下单渠道
     */
    @ApiModelProperty(value = "下单渠道：1-WEB单票；2-WEB导入；3-jvgo；4-api单个；5-api批量", required = true)
    @NotNull(message = "RULE_ORDER_CHANNEL_IS_ERROR")
    @Range(min = 1, max = 5, message = "RULE_ORDER_CHANNEL_IS_ERROR")
    private Integer orderChannel;

    /**
     * 月结卡号白名单，逗号分隔
     */
    @ApiModelProperty(value = "月结卡号白名单")
    private String monthCardNoWhite;
}
