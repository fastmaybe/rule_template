package com.sf.saas.bsp.rule.controller.manager;

import com.sf.saas.bsp.rule.core.api.IRuleInfoBizService;
import com.sf.saas.bsp.rule.core.common.constans.ResponseCodeEnum;
import com.sf.saas.bsp.rule.core.common.utils.ResponseHelper;
import com.sf.saas.bsp.rule.core.dto.base.Response;
import com.sf.saas.bsp.rule.core.dto.req.LoadOrderPageBaseRuleQueryVo;
import com.sf.saas.bsp.rule.core.dto.res.LoadOrderPageBaseRuleQueryResp;
import com.sf.saas.bsp.rule.core.service.utils.CurrentReqInfoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Description BaseRuleController
 *
 * @author suntao(01408885)
 * @since 2022-12-26
 */
@Log4j2
@RestController
@RequestMapping("/base/rule")
@Api(tags = "基础规则拉取API")
public class BaseRuleController {


    @Autowired
    private IRuleInfoBizService ruleInfoBizService;



    /**
     * 下单域  拉取基础规则
     * @param loadOrderPageBaseRuleQueryVo
     * @return
     */
    @PostMapping("/loadBaseRule")
    @ApiOperation(value = "下单域拉取基础规则")
    public Response<LoadOrderPageBaseRuleQueryResp> loadBaseRule(@Valid @RequestBody LoadOrderPageBaseRuleQueryVo loadOrderPageBaseRuleQueryVo){

        String tenantId = CurrentReqInfoUtil.currentTenantId();
        if (StringUtils.isBlank(tenantId)) {
            log.info("rule loadBaseRule called, user:{},tenantId:is null", CurrentReqInfoUtil.currentUser());
            return ResponseHelper.buildFail(ResponseCodeEnum.TENANT_ID_IS_ERROR);
        }

        log.info("rule loadBaseRule called, user:{},tenantId:{}", CurrentReqInfoUtil.currentUser(), tenantId);
        loadOrderPageBaseRuleQueryVo.setTenantId(tenantId);
        return ruleInfoBizService.loadWebBaseRule(loadOrderPageBaseRuleQueryVo);
    }
}
