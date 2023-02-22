package com.sf.saas.bsp.rule.controller.inner;

import cn.hutool.json.JSONObject;
import com.sf.saas.bsp.rule.core.api.IRuleCheckBizService;
import com.sf.saas.bsp.rule.core.dto.base.AppResponse;
import com.sf.saas.bsp.rule.core.dto.res.CheckResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 01407460
 * @date 2022/10/31 11:20
 */

@Slf4j
@RestController
@RequestMapping("/rule")
@Api(tags = "【规则校验器】")
public class RuleCheckControlController {




    @Autowired
    private IRuleCheckBizService ruleCheckBizService;

    @PostMapping("/check")
    @ApiOperation(value = "单票校验")
    public AppResponse<CheckResult> checkRuleSingle(@RequestBody JSONObject jsonObject) {

       return ruleCheckBizService.checkRuleSingle(jsonObject);
    }



    @PostMapping("/check/batch")
    @ApiOperation(value = "多票校验")
    public AppResponse<List<CheckResult>> checkRuleBatch(@RequestBody List<JSONObject> param) {


        return ruleCheckBizService.checkRuleBatch(param);
    }
}
