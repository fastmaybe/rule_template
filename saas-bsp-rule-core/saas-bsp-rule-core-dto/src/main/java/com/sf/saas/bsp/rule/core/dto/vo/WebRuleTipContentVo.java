package com.sf.saas.bsp.rule.core.dto.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Description StrategyRemarkVo
 *
 * @author suntao(01408885)
 * @since 2022-09-08
 */
@Data
public class WebRuleTipContentVo {

    /**
     * 语种
     */
    @ApiModelProperty(value = "语种", required = true)
    @NotBlank(message = "RULE_LANG_IS_EMPTY")
    private String lang;

    /**
     * 备注说明
     */
    @ApiModelProperty(value = "备注说明", required = true)
    @NotBlank(message = "RULE_LANG_CONTENT_EMPTY")
    private String content;

    /**
     * 前端/后端提示：0-后端；1-前端
     */
    @ApiModelProperty(value = "前端/后端提示：0-后端；1-前端", required = true)
    @NotNull(message = "RULE_LANG_TYPE_ERROR")
    @Range(min = 0, max = 1, message = "RULE_LANG_TYPE_ERROR")
    private Integer tipType;
}
