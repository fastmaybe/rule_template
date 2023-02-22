package com.sf.saas.bsp.rule.core.dto.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Description LogQueryPageVo
 *
 * @author suntao(01408885)
 * @since 2022-11-02
 */
@Data
public class LoadOrderPageBaseRuleQueryResp {
    /**
     * tenantId
     */
    @ApiModelProperty(value = "tenantId")
    private String tenantId;

    /**
     * 下单渠道
     */
    @ApiModelProperty(value = "下单渠道：1-WEB单票；2-WEB导入；3-jvgo；4-api单个；5-api批量", required = true)
    private Integer orderChannel;

    /**
     * 月结卡号白名单，逗号分隔
     */
    @ApiModelProperty(value = "月结卡号白名单（如果请求的传了才有，没传就没有）")
    private String monthCardNoWhite;

    @ApiModelProperty(value = "字段规则")
    private Map<String, List<LoadOrderPageBaseRuleFieldInfo>> fieldKeyInfoMap;


}
