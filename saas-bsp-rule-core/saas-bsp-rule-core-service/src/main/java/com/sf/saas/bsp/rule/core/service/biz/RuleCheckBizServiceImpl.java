package com.sf.saas.bsp.rule.core.service.biz;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.sf.saas.bsp.rule.core.api.IRuleCheckBizService;
import com.sf.saas.bsp.rule.core.common.constans.BspRuleConstant;
import com.sf.saas.bsp.rule.core.common.constans.ResponseCodeEnum;
import com.sf.saas.bsp.rule.core.common.utils.ResponseHelper;
import com.sf.saas.bsp.rule.core.dto.base.AppResponse;
import com.sf.saas.bsp.rule.core.dto.res.CheckResult;
import com.sf.saas.bsp.rule.core.service.crud.IRuleInfoService;
import com.sf.saas.bsp.rule.core.service.rule.biz.RuleCondEnum;
import com.sf.saas.bsp.rule.core.service.rule.biz.ValidatorTemplate;
import com.sf.saas.bsp.rule.core.service.rule.bo.RuleConfPool;
import com.sf.saas.bsp.rule.core.service.utils.CurrentReqInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author 01407460
 * @date 2022/10/31 11:34
 */
@Slf4j
@Service
public class RuleCheckBizServiceImpl implements IRuleCheckBizService {

    @Autowired
    private ThreadPoolTaskExecutor ruleCheckExecutor;

    @Autowired
    private IRuleInfoService ruleInfoService;

    @Autowired
    private ValidatorTemplate validatorTemplate;

    @Override
    public AppResponse<CheckResult> checkRuleSingle(JSONObject jsonObject) {


        String tenantId = jsonObject.getStr(BspRuleConstant.HEADER_TENANT_ID);
        String orderChannel = jsonObject.getStr(RuleCondEnum.ORDER_CHANNEL.getField());
        if (StringUtils.isBlank(tenantId)) {
            return ResponseHelper.buildAppFail(ResponseCodeEnum.TENANT_ID_IS_NULL);
        }
        if (StringUtils.isBlank(orderChannel)) {
            return ResponseHelper.buildAppFail(ResponseCodeEnum.ORDER_CHANNEL_IS_ERROR);
        }


        RuleConfPool rulePool = ruleInfoService.getRulePool(tenantId);

        log.info("checkRule pool={}", JSON.toJSONString(rulePool));

        String currentLang = CurrentReqInfoUtil.currentLang();
        try {
            CheckResult validate = validatorTemplate.validate(rulePool, jsonObject,currentLang);
            return ResponseHelper.buildAppSuccess(validate);
        } catch (Exception e) {
            //出现异常 默认通过校验放行  不阻塞流程
            log.error("checkRuleSingle found error,params is {} .", JSONUtil.toJsonStr(jsonObject), e);
        }

        String waybillNo = jsonObject.getStr(RuleCondEnum.WAYBILLNO.getField());
        return ResponseHelper.buildAppSuccess(CheckResult.buildDefault(waybillNo));
    }


    @Override
    public AppResponse<List<CheckResult>> checkRuleBatch(List<JSONObject> param) {
        if (CollectionUtils.isEmpty(param)) {
            return ResponseHelper.buildAppSuccess();
        }
        if (param.size() > BspRuleConstant.BATCH_LIMIT) {
            return ResponseHelper.buildAppFail(ResponseCodeEnum.STRATEGY_BATCH_OUT_OF_RANGE);
        }
        String tenantId = param.get(0).getStr(BspRuleConstant.HEADER_TENANT_ID);
        if (StringUtils.isBlank(tenantId)) {
            return ResponseHelper.buildAppFail(ResponseCodeEnum.TENANT_ID_IS_NULL);
        }

        String currentLang = CurrentReqInfoUtil.currentLang();

        int partitionSize = param.size() > BspRuleConstant.BATCH_LIMIT / 2 ? 6 : 4;
        List<List<JSONObject>> partition = Lists.partition(param, partitionSize);
        RuleConfPool rulePool = ruleInfoService.getRulePool(tenantId);

        log.info("checkRule pool={}", JSON.toJSONString(rulePool));
        List<CompletableFuture<List<CheckResult>>> futureList = partition.stream().map(onePartition -> CompletableFuture.supplyAsync(() -> {
            List<CheckResult> res = new ArrayList<>();
            for (JSONObject jsonObject : onePartition) {

                CheckResult validate;
                try {
                    String orderChannel = jsonObject.getStr(RuleCondEnum.ORDER_CHANNEL.getField());
                    if (StringUtils.isBlank(orderChannel)) {
                        validate = CheckResult.buildFail(jsonObject.getStr(RuleCondEnum.WAYBILLNO.getField()));
                    } else {
                        validate = validatorTemplate.validate(rulePool, jsonObject, currentLang);
                    }

                } catch (Exception e) {
                    //出现异常 默认通过校验放行  不阻塞流程
                    log.error("checkRuleBatch found error,params is {} .", JSONUtil.toJsonStr(jsonObject), e);
                    validate = CheckResult.buildDefault(jsonObject.getStr(RuleCondEnum.WAYBILLNO.getField()));
                }
                res.add(validate);
            }
            return res;
        }, ruleCheckExecutor)).collect(Collectors.toList());
        List<CheckResult> allResult = new ArrayList<>();
        for (CompletableFuture<List<CheckResult>> future : futureList) {
            try {
                List<CheckResult> checkResults = Optional.ofNullable(future.get(45, TimeUnit.SECONDS)).orElseGet(ArrayList::new);
                allResult.addAll(checkResults);
            } catch (Exception e) {
                log.error("checkRuleBatch get result  error...", e);
            }
        }

        return ResponseHelper.buildAppSuccess(allResult);
    }
}
