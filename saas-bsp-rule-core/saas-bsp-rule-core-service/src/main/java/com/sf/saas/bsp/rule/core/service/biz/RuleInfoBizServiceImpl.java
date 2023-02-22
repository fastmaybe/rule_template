package com.sf.saas.bsp.rule.core.service.biz;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.sf.saas.bsp.rule.core.api.IRuleInfoBizService;
import com.sf.saas.bsp.rule.core.api.IRuleOperationLogBizService;
import com.sf.saas.bsp.rule.core.api.IRuleTipContentBizService;
import com.sf.saas.bsp.rule.core.common.anno.RedisLock;
import com.sf.saas.bsp.rule.core.common.constans.BspRuleConstant;
import com.sf.saas.bsp.rule.core.common.constans.CommonStatusEnum;
import com.sf.saas.bsp.rule.core.common.constans.ResponseCodeEnum;
import com.sf.saas.bsp.rule.core.common.emnus.BaseRuleFieldNameEnum;
import com.sf.saas.bsp.rule.core.common.emnus.LockLevel;
import com.sf.saas.bsp.rule.core.common.emnus.OrderChannelTypeEnum;
import com.sf.saas.bsp.rule.core.common.utils.ResponseHelper;
import com.sf.saas.bsp.rule.core.common.utils.TenantIdUtil;
import com.sf.saas.bsp.rule.core.common.utils.ValidCodeMsg;
import com.sf.saas.bsp.rule.core.common.utils.ValidCodeMsgTuple;
import com.sf.saas.bsp.rule.core.dao.entity.RuleInfo;
import com.sf.saas.bsp.rule.core.dao.entity.RuleTenantFromTemplate;
import com.sf.saas.bsp.rule.core.dto.base.Response;
import com.sf.saas.bsp.rule.core.dto.base.WebPage;
import com.sf.saas.bsp.rule.core.dto.bo.OperationLogBo;
import com.sf.saas.bsp.rule.core.dto.bo.condition.base.BaseRuleConditionValueBo;
import com.sf.saas.bsp.rule.core.dto.bo.condition.pre.ConditionRuleFieldKeyConfig;
import com.sf.saas.bsp.rule.core.dto.bo.condition.pre.ConditionRuleFieldValueConfig;
import com.sf.saas.bsp.rule.core.dto.bo.condition.pre.ConditionRuleOutJoinerConfig;
import com.sf.saas.bsp.rule.core.dto.req.LoadOrderPageBaseRuleQueryVo;
import com.sf.saas.bsp.rule.core.dto.req.RuleVoQueryReq;
import com.sf.saas.bsp.rule.core.dto.res.LoadOrderPageBaseRuleFieldInfo;
import com.sf.saas.bsp.rule.core.dto.res.LoadOrderPageBaseRuleQueryResp;
import com.sf.saas.bsp.rule.core.dto.vo.CopyLogBo;
import com.sf.saas.bsp.rule.core.dto.vo.RuleInfoVo;
import com.sf.saas.bsp.rule.core.manager.convert.RuleInfoConvert;
import com.sf.saas.bsp.rule.core.manager.convert.RuleTenantFromTemplateConvert;
import com.sf.saas.bsp.rule.core.service.crud.IRuleInfoService;
import com.sf.saas.bsp.rule.core.service.crud.IRuleTenantFromTemplateService;
import com.sf.saas.bsp.rule.core.service.rule.ConditionEnum;
import com.sf.saas.bsp.rule.core.service.rule.param.valid.IRuleConditionParamValueValidator;
import com.sf.saas.bsp.rule.core.service.rule.param.valid.RuleConditionValidatorHandler;
import com.sf.saas.bsp.rule.core.service.rule.param.valid.RulePreConditionConfigBaseValidator;
import com.sf.saas.bsp.rule.core.service.utils.ConditionUtil;
import com.sf.saas.bsp.rule.core.service.utils.CurrentReqInfoUtil;
import com.sf.saas.bsp.rule.core.service.utils.RuleOperationUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description RuleInfoBizServiceImpl
 *
 * @author suntao(01408885)
 * @since 2022-10-26
 */

@Log4j2
@Service
public class RuleInfoBizServiceImpl implements IRuleInfoBizService {

    @Autowired
    private IRuleInfoService ruleInfoService;
    @Autowired
    private IRuleOperationLogBizService ruleOperationLogBizService;
    @Autowired
    private IRuleTipContentBizService ruleTipContentBizService;
    @Autowired
    private IRuleTenantFromTemplateService ruleTenantFromTemplateService;

    private Map<String, IRuleConditionParamValueValidator> ruleConditionValidatorMap;


    @Value("${auto.load.tenant.template:0}")
    private Integer autoLoadTenantTemplate;

    @Autowired
    public RuleInfoBizServiceImpl(Set<IRuleConditionParamValueValidator> handlerSet) {
        Assert.notEmpty(handlerSet);
        ImmutableMap.Builder<String, IRuleConditionParamValueValidator> builder = ImmutableMap.builder();
        for (IRuleConditionParamValueValidator handler : handlerSet) {
            builder.put(handler.getType(), handler);
        }
        this.ruleConditionValidatorMap = builder.build();
    }


    @Override
    public WebPage<RuleInfoVo> queryPage(RuleVoQueryReq query) {
        query.setTenantId(CurrentReqInfoUtil.currentTenantId());
        return ruleInfoService.queryPageXml(query);
    }

    @Transactional
    @Override
    @RedisLock(lockLevel = LockLevel.GLOBAL, preKey = BspRuleConstant.RULE_UPDATE, keys = {"configName", "fieldKey"})
    public Response<String> saveOrUpdate(RuleInfoVo vo) {
        String currentTenantId = CurrentReqInfoUtil.currentTenantId();
        String currentUser = CurrentReqInfoUtil.currentUser();

        if (CommonStatusEnum.STATUS_NOT_ENABLED.getStatus().equals(vo.getRuleTemplate())) {
            // 不是模板 是租户数据
            vo.setTenantId(currentTenantId);
        }

        // 验证必填
        if (!OrderChannelTypeEnum.containsWithString(vo.getOrderChannel())) {
            log.info("saveOrUpdate 下单类型选择错误-orderChannel={}", vo.getOrderChannel());
            return ResponseHelper.buildFail(ResponseCodeEnum.RULE_ORDER_CHANNEL_IS_ERROR);
        }
        // 前端key字段和后端key字段 至少一个不能为空
        if (StringUtils.isBlank(vo.getFieldKey()) && StringUtils.isBlank(vo.getWebFieldName())) {
            log.info("saveOrUpdate 前端key字段和后端key字段 至少一个不能为空-fieldKey={},webFieldName={}", vo.getFieldKey(), vo.getWebFieldName());
            return ResponseHelper.buildFail(ResponseCodeEnum.RULE_FIELD_KEY_CANNOT_NULL);
        }

        /**   =========   基础规则校验 begin  ============  */
        List<BaseRuleConditionValueBo> baseRuleConditionValueBos = JSON.parseArray(vo.getRuleCondition(), BaseRuleConditionValueBo.class);
        if (CollectionUtils.isEmpty(baseRuleConditionValueBos)) {
            log.info("saveOrUpdate 基础规则校验未通过，基础规则为空-ruleCondition={}", vo.getRuleCondition());
            return ResponseHelper.buildFail(ResponseCodeEnum.BASE_RULE_IS_ERROR);
        }
        // 校验
        ValidCodeMsg<ResponseCodeEnum> validateBaseRule = validateBaseRule(baseRuleConditionValueBos);
        if (validateBaseRule.isErr()) {
            return ResponseHelper.buildFail(validateBaseRule.getData());
        }
        // 前置规则 转 后台计算需要json
        ConditionRuleOutJoinerConfig convertRuleCondition2PreCondition = ConditionUtil.convertRuleCondition2PreCondition(vo.getFieldKey(), baseRuleConditionValueBos);
        vo.setRuleConditionBackstage(JSON.toJSONString(convertRuleCondition2PreCondition));
        /**   =========   基础规则校验 end  ============== */

        /**   =========   前置规则校验 及 md5计算 begin  ==============  */
        if (StringUtils.isNotBlank(vo.getPreCondition())) {
            ValidCodeMsg<ResponseCodeEnum> validatePreRule = validatePreRuleAndPreMd5(vo);
            if (validatePreRule.isErr()) {
                return ResponseHelper.buildFail(validatePreRule.getData());
            }
        } else {
            vo.setPreConditionMd5(null);
        }
        //   =========   前置规则校验 及 md5计算 end  ==============


        // 数据是否存在
        boolean isNew = false;
        if (vo.getId() != null) {
            RuleInfo db = ruleInfoService.getById(vo.getId());
            if (db == null) {
                log.info("updateStatus 更新数据不存在-vo={}", JSON.toJSONString(vo));
                return ResponseHelper.buildFail(ResponseCodeEnum.RULE_IS_NOT_EXIST);
            }
            vo.setTenantId(db.getTenantId());
            vo.setState(db.getState());
        } else {
            // 新增 默认生效
            isNew = true;
            vo.setState(CommonStatusEnum.STATUS_ENABLED.getStatus());
        }
        // 验证租户
        boolean validTenantId = TenantIdUtil.validTenantId(vo.getTenantId(), currentTenantId, currentUser, vo.getRuleTemplate());
        if (!validTenantId) {
            log.info("saveOrUpdate 租户不匹配-ruleName={}", vo.getConfigName());
            return ResponseHelper.buildFail(ResponseCodeEnum.TENANT_ID_IS_ERROR);
        }

        // 下单方式 + 规则类型 + 前置条件 + 字段不能重复
        RuleInfo exist = ruleInfoService.uniquenessCheck(vo, vo.getId());
        if (exist != null) {
            log.info("saveOrUpdate规则重复仅做提示-reqVo={}", JSON.toJSONString(vo));
        }

        RuleInfo ruleInfo = RuleInfoConvert.convertRuleInfoVo2Do(vo);
        // 创建时间 修改时间 操作人
        ruleInfo.setGmtModified(System.currentTimeMillis());
        ruleInfo.setModifiedBy(currentUser);
        // 保存
        ruleInfoService.saveOrUpdate(ruleInfo);
        vo.setId(ruleInfo.getId());

        ruleTipContentBizService.saveTipsContentList(vo, vo.getRuleTipContentVos());

        // 日志
        OperationLogBo operationLogBo = new OperationLogBo();
        operationLogBo.setUser(currentUser);
        operationLogBo.setRuleId(ruleInfo.getId());
        operationLogBo.setTenantId(ruleInfo.getTenantId());
        operationLogBo.setGmtModified(ruleInfo.getGmtModified());
        operationLogBo.setTypeEnum(RuleOperationUtil.getNewOrUpdateType(isNew));
        operationLogBo.setRequestData(vo);

        ruleOperationLogBizService.optLog(operationLogBo);

        return ResponseHelper.buildSuccess();
    }

    @Override
    public Response<String> uniquenessCheck(RuleInfoVo vo) {
        String currentTenantId = CurrentReqInfoUtil.currentTenantId();
        String currentUser = CurrentReqInfoUtil.currentUser();

        vo.setTenantId(currentTenantId);

        // 验证必填
        if (!OrderChannelTypeEnum.containsWithString(vo.getOrderChannel())) {
            log.info("uniquenessCheck 下单类型选择错误-orderChannel={}", vo.getOrderChannel());
            return ResponseHelper.buildFail(ResponseCodeEnum.RULE_ORDER_CHANNEL_IS_ERROR);
        }


        /**   =========   前置规则校验 及 md5计算 begin  ==============  */
        if (StringUtils.isNotBlank(vo.getPreCondition())) {
            ValidCodeMsg<ResponseCodeEnum> validatePreRule = validatePreRuleAndPreMd5(vo);
            if (validatePreRule.isErr()) {
                return ResponseHelper.buildFail(validatePreRule.getData());
            }
        }
        //   =========   前置规则校验 及 md5计算 end  ==============


        // 数据是否存在
        if (vo.getId() != null) {
            RuleInfo db = ruleInfoService.getById(vo.getId());
            if (db == null) {
                log.info("uniquenessCheck 更新数据不存在-vo={}", JSON.toJSONString(vo));
                return ResponseHelper.buildFail(ResponseCodeEnum.RULE_IS_NOT_EXIST);
            }
            vo.setTenantId(db.getTenantId());
        }
        // 验证租户
        boolean validTenantId = TenantIdUtil.validTenantId(vo.getTenantId(), currentTenantId, currentUser, vo.getRuleTemplate());
        if (!validTenantId) {
            log.info("uniquenessCheck 租户不匹配-ruleName={}", vo.getConfigName());
            return ResponseHelper.buildFail(ResponseCodeEnum.TENANT_ID_IS_ERROR);
        }

        // 下单方式 + 规则类型 + 前置条件 + 字段不能重复
        RuleInfo exist = ruleInfoService.uniquenessCheck(vo, vo.getId());
        if (exist != null) {
            log.info("uniquenessCheck 规则重复仅做提示-reqVo={}", JSON.toJSONString(vo));
            return ResponseHelper.buildFailWithErrorData(ResponseCodeEnum.RULE_IS_REPEAT, "repeat id:" + exist.getId());
        }

        return ResponseHelper.buildSuccess();
    }

    @Transactional
    @Override
    @RedisLock(lockLevel = LockLevel.GLOBAL, preKey = BspRuleConstant.RULE_UPDATE_STATE, keys = {"id"})
    public Response<String> updateStatus(RuleInfoVo vo) {
        if (vo == null || vo.getId() == null || vo.getState() == null) {
            log.info("updateStatus 未选择数据-vo={}", JSON.toJSONString(vo));
            return ResponseHelper.buildFail(ResponseCodeEnum.RULE_IS_NOT_EXIST);
        }

        RuleInfo ruleInfo = ruleInfoService.getById(vo.getId());
        if (ruleInfo == null) {
            log.info("updateStatus 规则不存在-vo={}", JSON.toJSONString(vo));
            return ResponseHelper.buildFail(ResponseCodeEnum.FAILURE);
        }

        Integer sourceState = ruleInfo.getState();
        if (vo.getState().equals(sourceState)) {
            log.info("状态无变化,无需修改id={}", ruleInfo.getId());
            return ResponseHelper.buildSuccess();
        }

        String currentUser = CurrentReqInfoUtil.currentUser();
        // 验证租户
        boolean validTenantId = TenantIdUtil.validTenantId(ruleInfo.getTenantId(), CurrentReqInfoUtil.currentTenantId(), currentUser);
        if (!validTenantId) {
            log.info("saveOrUpdate 租户不匹配-ruleName={}", vo.getConfigName());
            return ResponseHelper.buildFail(ResponseCodeEnum.TENANT_ID_IS_ERROR);
        }


        ruleInfo.setGmtModified(System.currentTimeMillis());
        ruleInfo.setModifiedBy(CurrentReqInfoUtil.currentUser());

        ruleInfo.setState(vo.getState());
        ruleInfoService.updateById(ruleInfo);

        // 日志
        OperationLogBo operationLogBo = new OperationLogBo();
        operationLogBo.setUser(currentUser);
        operationLogBo.setRuleId(ruleInfo.getId());
        operationLogBo.setTenantId(ruleInfo.getTenantId());
        operationLogBo.setGmtModified(ruleInfo.getGmtModified());
        operationLogBo.setTypeEnum(RuleOperationUtil.getStateType(sourceState, vo.getState()));
        operationLogBo.setRequestData(vo);

        ruleOperationLogBizService.optLog(operationLogBo);

        return ResponseHelper.buildSuccess();
    }

    @Override
    public Response<LoadOrderPageBaseRuleQueryResp> loadWebBaseRule(LoadOrderPageBaseRuleQueryVo loadOrderPageBaseRuleQueryVo) {
        if (CommonStatusEnum.STATUS_ENABLED.getStatus().equals(autoLoadTenantTemplate)) {
            //是否自动通过加载基础规则 初始化租户规则 默认否
            if (!tenantHasInit(loadOrderPageBaseRuleQueryVo.getTenantId())) {
                // 没有初始化过  先初始化
                try {
                    Response response = tenantInit(loadOrderPageBaseRuleQueryVo.getTenantId());
                    log.info("loadWebBaseRule 初始化模板结果-response={}", JSON.toJSONString(response));
                } catch (Exception e) {
                    log.info("loadWebBaseRule 初始化模板错误-e={}", ExceptionUtils.getStackTrace(e));
                }
            }
        }
        List<RuleInfoVo> ruleInfoVos = ruleInfoService.loadWebBaseRule(loadOrderPageBaseRuleQueryVo);

        List<LoadOrderPageBaseRuleFieldInfo> loadOrderPageBaseRuleFieldInfos = RuleInfoConvert.convertVo2LoadOrderPageBaseRuleFieldInfo(ruleInfoVos);

        Map<String, List<LoadOrderPageBaseRuleFieldInfo>> collect = loadOrderPageBaseRuleFieldInfos.stream().collect(Collectors.groupingBy(LoadOrderPageBaseRuleFieldInfo::getWebFieldName));

        LoadOrderPageBaseRuleQueryResp resp = new LoadOrderPageBaseRuleQueryResp();
        resp.setTenantId(loadOrderPageBaseRuleQueryVo.getTenantId());
        resp.setOrderChannel(loadOrderPageBaseRuleQueryVo.getOrderChannel());
        resp.setMonthCardNoWhite(loadOrderPageBaseRuleQueryVo.getMonthCardNoWhite());
        resp.setFieldKeyInfoMap(collect);

        return ResponseHelper.buildSuccess(resp);
    }

    @Transactional
    @Override
    public Response tenantInit(String tenantId) {
        if (StringUtils.isBlank(tenantId)) {
            return ResponseHelper.buildFail();
        }
        String currentUser = CurrentReqInfoUtil.currentUser();
        // 验证租户
        boolean validTenantId = TenantIdUtil.validTenantId(tenantId, CurrentReqInfoUtil.currentTenantId(), currentUser);
        if (!validTenantId) {
            log.info("tenantInit 租户不匹配-reqTenantId={}, currentTenant={}", tenantId, CurrentReqInfoUtil.currentTenantId());
            return ResponseHelper.buildFail(ResponseCodeEnum.TENANT_ID_IS_ERROR);
        }
        RuleTenantFromTemplate ruleTenantFromTemplate = ruleTenantFromTemplateService.getByTenantId(tenantId);
        if (tenantHasInit(tenantId)) {
            // 已经复制过 不再复制
            log.info("tenantInit 已经复制过-reqTenantId={}, currentTenant={}", tenantId, CurrentReqInfoUtil.currentTenantId());
            return ResponseHelper.buildFail(ResponseCodeEnum.RULE_HAS_COPIED_ERROR);
        }
        List<RuleInfoVo> ruleInfoList = ruleInfoService.selectTemplateList();

        for (RuleInfoVo ruleInfoVo : ruleInfoList) {

            Long templateRuleId = ruleInfoVo.getId();

            RuleInfo ruleInfo = RuleTenantFromTemplateConvert.convertRuleInfoCopyFromTemplate2TenantRule(ruleInfoVo, tenantId);
            // 保存规则
            ruleInfoService.save(ruleInfo);
            ruleInfoVo.setId(ruleInfo.getId());

            ruleTipContentBizService.saveCopyTipsContentListFromTemplate(ruleInfoVo, ruleInfoVo.getRuleTipContentVos(), tenantId);

            // 日志
            OperationLogBo operationLogBo = new OperationLogBo();
            operationLogBo.setUser(currentUser);
            operationLogBo.setRuleId(ruleInfo.getId());
            operationLogBo.setTenantId(ruleInfo.getTenantId());
            operationLogBo.setGmtModified(ruleInfo.getGmtModified());
            operationLogBo.setTypeEnum(RuleOperationUtil.getNewOrUpdateType(true));
            operationLogBo.setRequestData(new CopyLogBo(templateRuleId, ruleInfoVo));

            ruleOperationLogBizService.optLog(operationLogBo);
        }
        // 更新 改成已复制
        ruleTenantFromTemplateService.updateRuleTemplate(ruleTenantFromTemplate, tenantId, CommonStatusEnum.STATUS_ENABLED.getStatus());
        return ResponseHelper.buildSuccess();
    }

    @Transactional
    @Override
    public Response forceTenantInit(String tenantId) {
        if (StringUtils.isBlank(tenantId)) {
            return ResponseHelper.buildFail();
        }
        String currentUser = CurrentReqInfoUtil.currentUser();
        // 验证租户
        boolean validTenantId = TenantIdUtil.validTenantId(tenantId, CurrentReqInfoUtil.currentTenantId(), currentUser);
        if (!validTenantId) {
            log.info("tenantInit 租户不匹配-reqTenantId={}, currentTenant={}", tenantId, CurrentReqInfoUtil.currentTenantId());
            return ResponseHelper.buildFail(ResponseCodeEnum.TENANT_ID_IS_ERROR);
        }
        RuleTenantFromTemplate ruleTenantFromTemplate = ruleTenantFromTemplateService.getByTenantId(tenantId);
        if (tenantHasInit(tenantId)) {
            // 已经复制过 不再复制
            log.info("tenantInit 已经复制过-reqTenantId={}, currentTenant={}", tenantId, CurrentReqInfoUtil.currentTenantId());
            ruleTenantFromTemplate.setIsCopy(CommonStatusEnum.STATUS_NOT_ENABLED.getStatus());
            ruleTenantFromTemplateService.updateById(ruleTenantFromTemplate);

            // 删除来自规则复制的初始化的规则
            List<RuleInfo> copyFromTemplateRules = ruleInfoService.queryTenantInitFromTemplate(tenantId);
            if (CollectionUtils.isNotEmpty(copyFromTemplateRules)) {
                List<Long> copyFromTemplateRuleIds = copyFromTemplateRules.stream().map(RuleInfo::getId).collect(Collectors.toList());
                ruleInfoService.deleteByRuleIds(copyFromTemplateRuleIds);
                // 删除 初始化规则的 语言
                ruleTipContentBizService.deleteByRuleIds(copyFromTemplateRuleIds);
            }

        }
        tenantInit(tenantId);
        return ResponseHelper.buildSuccess();
    }

    @Transactional
    @Override
    public Response adminDeleteRuleIds(String ruleIds) {
        if (StringUtils.isBlank(ruleIds)) {
            return ResponseHelper.buildFail();
        }
        String currentUser = CurrentReqInfoUtil.currentUser();
        if (BspRuleConstant.ADMIN_USER.equals(currentUser)) {

            String[] ruleIdArr = ruleIds.split(BspRuleConstant.SPLIT_COMMON);

            List<Long> ruleIdList = Arrays.stream(ruleIdArr).map(Long::valueOf).collect(Collectors.toList());

            ruleInfoService.deleteByRuleIds(ruleIdList);
            // 删除 初始化规则的 语言
            ruleTipContentBizService.deleteByRuleIds(ruleIdList);
        }
        return ResponseHelper.buildSuccess();
    }

    @Transactional
    @Override
    public Response deleteByTenantId(String tenantId) {
        ruleInfoService.deleteByTenantId(tenantId);
        ruleTipContentBizService.deleteByTenantId(tenantId);
        return ResponseHelper.buildSuccess();
    }

    @Override
    public void updateRule_length_between_with_both(String tenantId) {
        List<RuleInfo> ruleInfos = ruleInfoService.selectByTenantId(tenantId);
        for (RuleInfo ruleInfo : ruleInfos) {
            String ruleCondition = ruleInfo.getRuleCondition();
            List<BaseRuleConditionValueBo> baseRuleConditionValueBos = JSON.parseArray(ruleCondition, BaseRuleConditionValueBo.class);
            if (CollectionUtils.isEmpty(baseRuleConditionValueBos)) {
                log.info("updateRule_length_between_with_both 基础规则校验未通过，基础规则为空-ruleCondition={}", ruleCondition);
                continue;
            }
            for (BaseRuleConditionValueBo baseRuleConditionValueBo : baseRuleConditionValueBos) {
                if (BaseRuleFieldNameEnum.FIELD_LENGTH.getFieldName().equals(baseRuleConditionValueBo.getInputKey())) {
                    baseRuleConditionValueBo.setCondition(ConditionEnum.LENGTH_BETWEEN_WITH_BOTH.name());
                }
            }
            ruleInfo.setRuleCondition(JSON.toJSONString(baseRuleConditionValueBos));
            ConditionRuleOutJoinerConfig convertRuleCondition2PreCondition = ConditionUtil.convertRuleCondition2PreCondition(ruleInfo.getFieldKey(), baseRuleConditionValueBos);
            ruleInfo.setRuleConditionBackstage(JSON.toJSONString(convertRuleCondition2PreCondition));
            ruleInfoService.updateById(ruleInfo);
        }
    }

    /**
     * 是否初始化过
     *
     * @param tenantId
     * @return true ：已经初始化模板
     * false：未初始化模板
     */
    public boolean tenantHasInit(String tenantId) {
        if (StringUtils.isBlank(tenantId)) {
            // 为空 不处理
            return true;
        }
        RuleTenantFromTemplate ruleTenantFromTemplate = ruleTenantFromTemplateService.getByTenantId(tenantId);
        if (ruleTenantFromTemplate != null && CommonStatusEnum.STATUS_ENABLED.getStatus().equals(ruleTenantFromTemplate.getIsCopy())) {
            // 已经复制过
            log.info("[tenantHasInit_{}] 已经初始化过, currentTenant={}", tenantId, CurrentReqInfoUtil.currentTenantId());
            return true;
        }
        log.info("[tenantHasInit_{}] 还没初始化模板, currentTenant={}", tenantId, CurrentReqInfoUtil.currentTenantId());
        return false;
    }


    /**
     * 前置规则校验  & 计算每个单元的md5
     *
     * @param vo
     * @return
     */
    private ValidCodeMsg<ResponseCodeEnum> validatePreRuleAndPreMd5(RuleInfoVo vo) {
        ConditionRuleOutJoinerConfig conditionRuleOutJoinerConfig = JSON.parseObject(vo.getPreCondition(), ConditionRuleOutJoinerConfig.class);
        ValidCodeMsg<ResponseCodeEnum> baseValidCodeMsg = RulePreConditionConfigBaseValidator.validConditionBase(conditionRuleOutJoinerConfig);
        if (baseValidCodeMsg.isErr()) {
            log.info("saveOrUpdate 前置规则校验未通过-preCondition={}", vo.getPreCondition());
            return ValidCodeMsg.error(baseValidCodeMsg.getData());
        }
        for (ConditionRuleFieldKeyConfig fieldKeyConfig : conditionRuleOutJoinerConfig.getConfigs()) {
            for (ConditionRuleFieldValueConfig valueConfig : fieldKeyConfig.getConfigs()) {
                ValidCodeMsgTuple<ResponseCodeEnum, String> validatorInstanceType = RuleConditionValidatorHandler.getValidatorInstanceType(valueConfig.getCondition());
                if (validatorInstanceType.isErr()) {
                    log.info("saveOrUpdate 前置规则校验未通过，参数校验未通过-baseRuleConditionValueBo={}", JSON.toJSONString(valueConfig));
                    return ValidCodeMsg.error(validatorInstanceType.getError());
                }
                IRuleConditionParamValueValidator ruleConditionParamValueValidator = ruleConditionValidatorMap.get(validatorInstanceType.getData());
                ValidCodeMsg<ResponseCodeEnum> validate = ruleConditionParamValueValidator.validate(valueConfig.getConfigs());
                if (validate.isErr()) {
                    log.info("saveOrUpdate 前置规则校验未通过-preCondition={}", vo.getPreCondition());
                    return ValidCodeMsg.error(validate.getData());
                }
                // 计算该单元的md5 值(单元：最小条件参数单元 )
                /*
                     "configs": [
                         "KR","CN"
                     ]
                 */
                String valueMd5 = ruleConditionParamValueValidator.getValueMd5(valueConfig.getConfigs());
                valueConfig.setUnitConfigsValueMd5(valueMd5);
            }
        }

        // 前置条件md5 设置
        vo.setPreConditionMd5(ConditionUtil.getPreConditionMd5(conditionRuleOutJoinerConfig));

        return ValidCodeMsg.success();
    }

    /**
     * 基础规则校验
     * 1.不能全部为空
     * 2.空的condition  isBlank=true；   isBlank=false或者null都是必填
     *
     * @param baseRuleConditionValueBos
     * @return
     */
    private ValidCodeMsg<ResponseCodeEnum> validateBaseRule(List<BaseRuleConditionValueBo> baseRuleConditionValueBos) {
        // 全部不为空 = false(初始化 全部为空)
        boolean allBlank = true;
        for (BaseRuleConditionValueBo baseRuleConditionValueBo : baseRuleConditionValueBos) {
            if (baseRuleConditionValueBo.confIsBlank()) {
                log.info("saveOrUpdate 基础规则项[{}]为空-baseRuleConditionValueBo={}", baseRuleConditionValueBo.getInputKey(), JSON.toJSONString(baseRuleConditionValueBo));
                continue;
            }
            // 至少一个不为空
            allBlank = false;

            ValidCodeMsgTuple<ResponseCodeEnum, String> validatorInstanceType = RuleConditionValidatorHandler.getValidatorInstanceType(baseRuleConditionValueBo.getCondition());
            if (validatorInstanceType.isErr()) {
                log.info("saveOrUpdate 基础规则校验未通过，检验规则中的，参数校验未通过-baseRuleConditionValueBo={}", JSON.toJSONString(baseRuleConditionValueBo));
                return ValidCodeMsg.error(ResponseCodeEnum.BASE_RULE_CONDITION_IS_ERROR);
            }
            IRuleConditionParamValueValidator ruleConditionParamValueValidator = ruleConditionValidatorMap.get(validatorInstanceType.getData());
            if (ruleConditionParamValueValidator == null) {
                log.info("saveOrUpdate 基础规则校验未通过,检验规则中的条件选择错误-baseRuleConditionValueBo={}", JSON.toJSONString(baseRuleConditionValueBo));
                return ValidCodeMsg.error(ResponseCodeEnum.BASE_RULE_CONDITION_IS_ERROR);
            }
            ValidCodeMsg<ResponseCodeEnum> validate = ruleConditionParamValueValidator.validate(baseRuleConditionValueBo.getConfigs());
            if (validate.isErr()) {
                log.info("saveOrUpdate 基础规则校验未通过，参数校验未通过-baseRuleConditionValueBo={}", JSON.toJSONString(baseRuleConditionValueBo));
                return ValidCodeMsg.error(validate.getData());
            }
        }
        if (allBlank) {
            // 基础规则全部为空
            log.info("saveOrUpdate 基础规则校验未通过，基础规则全部为空");
            return ValidCodeMsg.error(ResponseCodeEnum.BASE_RULE_CONDITION_IS_ALL_NULL);
        }
        // 全部校验通过 且  至少有一个不为空
        return ValidCodeMsg.success();
    }


}
