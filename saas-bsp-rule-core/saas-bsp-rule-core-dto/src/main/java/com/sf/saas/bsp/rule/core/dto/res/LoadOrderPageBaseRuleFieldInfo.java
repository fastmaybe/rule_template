package com.sf.saas.bsp.rule.core.dto.res;

import com.sf.saas.bsp.rule.core.dto.vo.RuleTipContentVo;
import com.sf.saas.bsp.rule.core.dto.vo.WebRuleTipContentVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

/**
 * Description LogQueryPageVo
 *
 * @author suntao(01408885)
 * @since 2022-11-02
 */
@Data
public class LoadOrderPageBaseRuleFieldInfo {

    /**
     * 前端字段
     */
    @ApiModelProperty(value = "规则字段")
    private String webFieldName;

    /**
     * 月结卡号白名单，逗号分隔
     */
    @ApiModelProperty(value = "月结卡号白名单，逗号分隔")
    private String monthCardNoWhite;

    @ApiModelProperty(value = "是否必填true-必填；false-非必填")
    private Boolean required;

    @ApiModelProperty(value = "正则表达式")
    private String regex;

    @ApiModelProperty(value = "字段长度区间")
    private List<Integer> fieldLength;

    @ApiModelProperty(value = "字段值区间")
    private LoadOrderPageBaseRuleRangInfo range;

    /**
     * 提示语，前端提示语
     */
    @ApiModelProperty(value = "提示语")
    private List<WebRuleTipContentVo> ruleTipContentVos;


}
