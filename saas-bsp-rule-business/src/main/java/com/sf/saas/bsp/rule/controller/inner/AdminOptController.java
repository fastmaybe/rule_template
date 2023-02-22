package com.sf.saas.bsp.rule.controller.inner;

import com.sf.saas.bsp.rule.core.api.IRuleInfoBizService;
import com.sf.saas.bsp.rule.core.api.IRuleTenantFromTemplateBizService;
import com.sf.saas.bsp.rule.core.api.IRuleTipContentBizService;
import com.sf.saas.bsp.rule.core.common.utils.ResponseHelper;
import com.sf.saas.bsp.rule.core.dto.base.Response;
import com.sf.saas.bsp.rule.core.service.utils.CurrentReqInfoUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description AdminOptController
 *
 * @author suntao(01408885)
 * @since 2023-02-16
 */
@Slf4j
@RestController
@RequestMapping("/adminOpt")
public class AdminOptController {
    @Autowired
    private IRuleInfoBizService ruleInfoBizService;
    @Autowired
    private IRuleTenantFromTemplateBizService ruleTenantFromTemplateBizService;
    @Autowired
    private IRuleTipContentBizService ruleTipContentBizService;


    @GetMapping("/tenantInit")
    //@ApiOperation(value = "初始化租户模板规则")
    public Response tenantInit(String tenantId) {
        log.info("rule tenantInit called, user[{}]-{}: tenantId[{}]", CurrentReqInfoUtil.currentUser(), CurrentReqInfoUtil.currentTenantId(), tenantId);
        return ruleInfoBizService.tenantInit(tenantId);
    }

    @GetMapping("/deleteByTenantId")
    //@ApiOperation(value = "初始化租户模板规则")
    public Response deleteByTenantId(String tenantId) {
        log.info("rule deleteByTenantId called, user[{}]-{}: tenantId[{}]", CurrentReqInfoUtil.currentUser(), CurrentReqInfoUtil.currentTenantId(), tenantId);
        return ruleInfoBizService.deleteByTenantId(tenantId);
    }

    @GetMapping("/tenantTemplateSetNotCopy")
    //@ApiOperation(value = "将已经标记初始化的租户标记成未初始化")
    public Response tenantTemplateSetNotCopy(String tenantId) {
        log.info("rule tenantTemplateSetNotCopy called, user[{}]-{}: tenantId[{}]", CurrentReqInfoUtil.currentUser(), CurrentReqInfoUtil.currentTenantId(), tenantId);
        ruleTenantFromTemplateBizService.tenantTemplateSetNotCopy(tenantId);
        return ResponseHelper.buildSuccess();
    }

    @PostMapping("/setRuleLang")
    //@ApiOperation(value = "给制定的ruleId设置en-us的语言")
    public Response setRuleLang(String ruleIds, String lang, Integer tipType) {
        log.info("rule setRuleLang called, user[{}]-{}:", CurrentReqInfoUtil.currentUser(), CurrentReqInfoUtil.currentTenantId());
        ruleTipContentBizService.setRuleLang(ruleIds, lang, tipType);
        return ResponseHelper.buildSuccess();
    }


    @GetMapping("/forceTenantInit")
    //@ApiOperation(value = "强制初始化租户模板规则")
    public Response forceTenantInit(String tenantId) {
        log.info("rule forceTenantInit called, user[{}]-{}: tenantId[{}]", CurrentReqInfoUtil.currentUser(), CurrentReqInfoUtil.currentTenantId(), tenantId);
        return ruleInfoBizService.forceTenantInit(tenantId);
    }


    @PostMapping("/adminDeleteRuleIds")
    //@ApiOperation(value = "强制删除")
    public Response adminDeleteRuleIds(String ruleIds) {
        log.info("rule adminDeleteRuleIds called, user[{}]-{}: ruleIds[{}]", CurrentReqInfoUtil.currentUser(), CurrentReqInfoUtil.currentTenantId(), ruleIds);
        return ruleInfoBizService.adminDeleteRuleIds(ruleIds);
    }

    @PostMapping("/updateRule_length_between_with_both")
    //@ApiOperation(value = "强制更新")
    public Response updateRule_length_between_with_both(String tenantId) {
        log.info("rule adminDeleteRuleIds called, user[{}]-{}", CurrentReqInfoUtil.currentUser(), CurrentReqInfoUtil.currentTenantId());

//        ruleInfoBizService.updateRule_length_between_with_both(tenantId);

        return ResponseHelper.buildSuccess();
    }


}
