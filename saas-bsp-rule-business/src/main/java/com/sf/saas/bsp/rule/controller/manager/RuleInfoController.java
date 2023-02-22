package com.sf.saas.bsp.rule.controller.manager;

import com.alibaba.fastjson.JSON;
import com.sf.saas.bsp.rule.core.api.IRuleInfoBizService;
import com.sf.saas.bsp.rule.core.api.IRuleOperationLogBizService;
import com.sf.saas.bsp.rule.core.common.constans.CommonStatusEnum;
import com.sf.saas.bsp.rule.core.common.utils.ResponseHelper;
import com.sf.saas.bsp.rule.core.dto.base.Response;
import com.sf.saas.bsp.rule.core.dto.base.WebPage;
import com.sf.saas.bsp.rule.core.dto.req.LogQueryPageVo;
import com.sf.saas.bsp.rule.core.dto.req.RuleVoQueryReq;
import com.sf.saas.bsp.rule.core.dto.vo.RuleInfoVo;
import com.sf.saas.bsp.rule.core.dto.vo.RuleOperationLogVo;
import com.sf.saas.bsp.rule.core.service.utils.CurrentReqInfoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 01407460
 * @since 2022/9/7 9:27
 */
@Log4j2
@RestController
@RequestMapping("/manage/rule")
@Api(tags = "规则信息API")
public class RuleInfoController {

    @Autowired
    private IRuleInfoBizService ruleInfoBizService;
    @Autowired
    private IRuleOperationLogBizService ruleOperationLogBizService;


    @PostMapping("/queryPage")
    @ApiOperation(value = "规则分页查询")
    public Response<WebPage<RuleInfoVo>> queryPage(@Valid @RequestBody RuleVoQueryReq query) {
        log.info("rule list called, user[{}]-{}:  req[{}]",
                 CurrentReqInfoUtil.currentUser(), CurrentReqInfoUtil.currentTenantId(), JSON.toJSON(query));
        if (query.getRuleTemplate() == null) {
            query.setRuleTemplate(CommonStatusEnum.STATUS_NOT_ENABLED.getStatus());
        }
        return ResponseHelper.buildSuccess(ruleInfoBizService.queryPage(query));
    }

    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "新增修改规则")
    public Response<String> saveOrUpdate(@Valid @RequestBody RuleInfoVo vo) {
        log.info("rule saveOrUpdate called, user[{}]-{}:  req[{}]",
                 CurrentReqInfoUtil.currentUser(), CurrentReqInfoUtil.currentTenantId(), JSON.toJSON(vo));
        if (vo.getRuleTemplate() == null) {
            vo.setRuleTemplate(CommonStatusEnum.STATUS_NOT_ENABLED.getStatus());
        }
        return ruleInfoBizService.saveOrUpdate(vo);
    }

    @PostMapping("/uniquenessCheck")
    @ApiOperation(value = "规则重复校验")
    public Response<String> uniquenessCheck(@Valid @RequestBody RuleInfoVo vo) {
        log.info("rule saveOrUpdate called, user[{}]-{}:  req[{}]",
                 CurrentReqInfoUtil.currentUser(), CurrentReqInfoUtil.currentTenantId(), JSON.toJSON(vo));
        return ruleInfoBizService.uniquenessCheck(vo);
    }


    @PostMapping("/updateStatus")
    @ApiOperation(value = "修改规则状态")
    public Response<String> updateStatus(@RequestBody RuleInfoVo vo){
        log.info("rule updateStatus called, user[{}]-{}: id[{}] status[{}]", CurrentReqInfoUtil.currentUser(), CurrentReqInfoUtil.currentTenantId(), vo.getId(), vo.getState());
        return ruleInfoBizService.updateStatus(vo);
    }


    @PostMapping("/logs")
    @ApiOperation(value = "查看日志")
    public Response<WebPage<RuleOperationLogVo>> queryRuleLogs(@Valid @RequestBody LogQueryPageVo queryPageVo){
        log.info("rule queryRuleLogs called, user[{}]-{}: id[{}]", CurrentReqInfoUtil.currentUser(), CurrentReqInfoUtil.currentTenantId(), queryPageVo.getRuleId());
        return ruleOperationLogBizService.queryRuleLogsPage(queryPageVo);
    }




}
