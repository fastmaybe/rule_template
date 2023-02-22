package com.sf.saas.bsp.rule.core.dto.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 01407460
 * @date 2022/10/31 11:46
 */

@Data
public class CheckResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "运单号")
    private String waybillNo ;

    @ApiModelProperty(value = "是否通过")
    private boolean pass ;

    @ApiModelProperty(value = "未通过的规则bo")
    private List<CheckValidResult> validResults;

    public static CheckResult buildDefault(String waybillNo){
        CheckResult result = new CheckResult();
        result.setWaybillNo(waybillNo);
        result.setPass(true);
        result.setValidResults(new ArrayList<>());
        return result;
    }

    public static CheckResult buildFail(String waybillNo){
        CheckResult result = new CheckResult();
        result.setWaybillNo(waybillNo);
        result.setPass(false);
        result.setValidResults(new ArrayList<>());
        return result;
    }

}
