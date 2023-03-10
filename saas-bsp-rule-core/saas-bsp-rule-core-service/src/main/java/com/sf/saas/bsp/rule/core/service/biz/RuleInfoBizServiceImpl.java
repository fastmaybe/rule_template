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
            // ???????????? ???????????????
            vo.setTenantId(currentTenantId);
        }

        // ????????????
        if (!OrderChannelTypeEnum.containsWithString(vo.getOrderChannel())) {
            log.info("saveOrUpdate ????????????????????????-orderChannel={}", vo.getOrderChannel());
            return ResponseHelper.buildFail(ResponseCodeEnum.RULE_ORDER_CHANNEL_IS_ERROR);
        }
        // ??????key???????????????key?????? ????????????????????????
        if (StringUtils.isBlank(vo.getFieldKey()) && StringUtils.isBlank(vo.getWebFieldName())) {
            log.info("saveOrUpdate ??????key???????????????key?????? ????????????????????????-fieldKey={},webFieldName={}", vo.getFieldKey(), vo.getWebFieldName());
            return ResponseHelper.buildFail(ResponseCodeEnum.RULE_FIELD_KEY_CANNOT_NULL);
        }

        /**   =========   ?????????????????? begin  ============  */
        List<BaseRuleConditionValueBo> baseRuleConditionValueBos = JSON.parseArray(vo.getRuleCondition(), BaseRuleConditionValueBo.class);
        if (CollectionUtils.isEmpty(baseRuleConditionValueBos)) {
            log.info("saveOrUpdate ????????????????????????????????????????????????-ruleCondition={}", vo.getRuleCondition());
            return ResponseHelper.buildFail(ResponseCodeEnum.BASE_RULE_IS_ERROR);
        }
        // ??????
        ValidCodeMsg<ResponseCodeEnum> validateBaseRule = validateBaseRule(baseRuleConditionValueBos);
        if (validateBaseRule.isErr()) {
            return ResponseHelper.buildFail(validateBaseRule.getData());
        }
        // ???????????? ??? ??????????????????json
        ConditionRuleOutJoinerConfig convertRuleCondition2PreCondition = ConditionUtil.convertRuleCondition2PreCondition(vo.getFieldKey(), baseRuleConditionValueBos);
        vo.setRuleConditionBackstage(JSON.toJSONString(convertRuleCondition2PreCondition));
        /**   =========   ?????????????????? end  ============== */

        /**   =========   ?????????????????? ??? md5?????? begin  ==============  */
        if (StringUtils.isNotBlank(vo.getPreCondition())) {
            ValidCodeMsg<ResponseCodeEnum> validatePreRule = validatePreRuleAndPreMd5(vo);
            if (validatePreRule.isErr()) {
                return ResponseHelper.buildFail(validatePreRule.getData());
            }
        } else {
            vo.setPreConditionMd5(null);
        }
        //   =========   ?????????????????? ??? md5?????? end  ==============


        // ??????????????????
        boolean isNew = false;
        if (vo.getId() != null) {
            RuleInfo db = ruleInfoService.getById(vo.getId());
            if (db == null) {
                log.info("updateStatus ?????????????????????-vo={}", JSON.toJSONString(vo));
                return ResponseHelper.buildFail(ResponseCodeEnum.RULE_IS_NOT_EXIST);
            }
            vo.setTenantId(db.getTenantId());
            vo.setState(db.getState());
        } else {
            // ?????? ????????????
            isNew = true;
            vo.setState(CommonStatusEnum.STATUS_ENABLED.getStatus());
        }
        // ????????????
        boolean validTenantId = TenantIdUtil.validTenantId(vo.getTenantId(), currentTenantId, currentUser, vo.getRuleTemplate());
        if (!validTenantId) {
            log.info("saveOrUpdate ???????????????-ruleName={}", vo.getConfigName());
            return ResponseHelper.buildFail(ResponseCodeEnum.TENANT_ID_IS_ERROR);
        }

        // ???????????? + ???????????? + ???????????? + ??????????????????
        RuleInfo exist = ruleInfoService.uniquenessCheck(vo, vo.getId());
        if (exist != null) {
            log.info("saveOrUpdate????????????????????????-reqVo={}", JSON.toJSONString(vo));
        }

        RuleInfo ruleInfo = RuleInfoConvert.convertRuleInfoVo2Do(vo);
        // ???????????? ???????????? ?????????
        ruleInfo.setGmtModified(System.currentTimeMillis());
        ruleInfo.setModifiedBy(currentUser);
        // ??????
        ruleInfoService.saveOrUpdate(ruleInfo);
        vo.setId(ruleInfo.getId());

        ruleTipContentBizService.saveTipsContentList(vo, vo.getRuleTipContentVos());

        // ??????
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

        // ????????????
        if (!OrderChannelTypeEnum.containsWithString(vo.getOrderChannel())) {
            log.info("uniquenessCheck ????????????????????????-orderChannel={}", vo.getOrderChannel());
            return ResponseHelper.buildFail(ResponseCodeEnum.RULE_ORDER_CHANNEL_IS_ERROR);
        }


        /**   =========   ?????????????????? ??? md5?????? begin  ==============  */
        if (StringUtils.isNotBlank(vo.getPreCondition())) {
            ValidCodeMsg<ResponseCodeEnum> validatePreRule = validatePreRuleAndPreMd5(vo);
            if (validatePreRule.isErr()) {
                return ResponseHelper.buildFail(validatePreRule.getData());
            }
        }
        //   =========   ?????????????????? ??? md5?????? end  ==============


        // ??????????????????
        if (vo.getId() != null) {
            RuleInfo db = ruleInfoService.getById(vo.getId());
            if (db == null) {
                log.info("uniquenessCheck ?????????????????????-vo={}", JSON.toJSONString(vo));
                return ResponseHelper.buildFail(ResponseCodeEnum.RULE_IS_NOT_EXIST);
            }
            vo.setTenantId(db.getTenantId());
        }
        // ????????????
        boolean validTenantId = TenantIdUtil.validTenantId(vo.getTenantId(), currentTenantId, currentUser, vo.getRuleTemplate());
        if (!validTenantId) {
            log.info("uniquenessCheck ???????????????-ruleName={}", vo.getConfigName());
            return ResponseHelper.buildFail(ResponseCodeEnum.TENANT_ID_IS_ERROR);
        }

        // ???????????? + ???????????? + ???????????? + ??????????????????
        RuleInfo exist = ruleInfoService.uniquenessCheck(vo, vo.getId());
        if (exist != null) {
            log.info("uniquenessCheck ????????????????????????-reqVo={}", JSON.toJSONString(vo));
            return ResponseHelper.buildFailWithErrorData(ResponseCodeEnum.RULE_IS_REPEAT, "repeat id:" + exist.getId());
        }

        return ResponseHelper.buildSuccess();
    }

    @Transactional
    @Override
    @RedisLock(lockLevel = LockLevel.GLOBAL, preKey = BspRuleConstant.RULE_UPDATE_STATE, keys = {"id"})
    public Response<String> updateStatus(RuleInfoVo vo) {
        if (vo == null || vo.getId() == null || vo.getState() == null) {
            log.info("updateStatus ???????????????-vo={}", JSON.toJSONString(vo));
            return ResponseHelper.buildFail(ResponseCodeEnum.RULE_IS_NOT_EXIST);
        }

        RuleInfo ruleInfo = ruleInfoService.getById(vo.getId());
        if (ruleInfo == null) {
            log.info("updateStatus ???????????????-vo={}", JSON.toJSONString(vo));
            return ResponseHelper.buildFail(ResponseCodeEnum.FAILURE);
        }

        Integer sourceState = ruleInfo.getState();
        if (vo.getState().equals(sourceState)) {
            log.info("???????????????,????????????id={}", ruleInfo.getId());
            return ResponseHelper.buildSuccess();
        }

        String currentUser = CurrentReqInfoUtil.currentUser();
        // ????????????
        boolean validTenantId = TenantIdUtil.validTenantId(ruleInfo.getTenantId(), CurrentReqInfoUtil.currentTenantId(), currentUser);
        if (!validTenantId) {
            log.info("saveOrUpdate ???????????????-ruleName={}", vo.getConfigName());
            return ResponseHelper.buildFail(ResponseCodeEnum.TENANT_ID_IS_ERROR);
        }


        ruleInfo.setGmtModified(System.currentTimeMillis());
        ruleInfo.setModifiedBy(CurrentReqInfoUtil.currentUser());

        ruleInfo.setState(vo.getState());
        ruleInfoService.updateById(ruleInfo);

        // ??????
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
            //???????????????????????????????????? ????????????????????? ?????????
            if (!tenantHasInit(loadOrderPageBaseRuleQueryVo.getTenantId())) {
                // ??????????????????  ????????????
                try {
                    Response response = tenantInit(loadOrderPageBaseRuleQueryVo.getTenantId());
                    log.info("loadWebBaseRule ?????????????????????-response={}", JSON.toJSONString(response));
                } catch (Exception e) {
                    log.info("loadWebBaseRule ?????????????????????-e={}", ExceptionUtils.getStackTrace(e));
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
        // ????????????
        boolean validTenantId = TenantIdUtil.validTenantId(tenantId, CurrentReqInfoUtil.currentTenantId(), currentUser);
        if (!validTenantId) {
            log.info("tenantInit ???????????????-reqTenantId={}, currentTenant={}", tenantId, CurrentReqInfoUtil.currentTenantId());
            return ResponseHelper.buildFail(ResponseCodeEnum.TENANT_ID_IS_ERROR);
        }
        RuleTenantFromTemplate ruleTenantFromTemplate = ruleTenantFromTemplateService.getByTenantId(tenantId);
        if (tenantHasInit(tenantId)) {
            // ??????????????? ????????????
            log.info("tenantInit ???????????????-reqTenantId={}, currentTenant={}", tenantId, CurrentReqInfoUtil.currentTenantId());
            return ResponseHelper.buildFail(ResponseCodeEnum.RULE_HAS_COPIED_ERROR);
        }
        List<RuleInfoVo> ruleInfoList = ruleInfoService.selectTemplateList();

        for (RuleInfoVo ruleInfoVo : ruleInfoList) {

            Long templateRuleId = ruleInfoVo.getId();

            RuleInfo ruleInfo = RuleTenantFromTemplateConvert.convertRuleInfoCopyFromTemplate2TenantRule(ruleInfoVo, tenantId);
            // ????????????
            ruleInfoService.save(ruleInfo);
            ruleInfoVo.setId(ruleInfo.getId());

            ruleTipContentBizService.saveCopyTipsContentListFromTemplate(ruleInfoVo, ruleInfoVo.getRuleTipContentVos(), tenantId);

            // ??????
            OperationLogBo operationLogBo = new OperationLogBo();
            operationLogBo.setUser(currentUser);
            operationLogBo.setRuleId(ruleInfo.getId());
            operationLogBo.setTenantId(ruleInfo.getTenantId());
            operationLogBo.setGmtModified(ruleInfo.getGmtModified());
            operationLogBo.setTypeEnum(RuleOperationUtil.getNewOrUpdateType(true));
            operationLogBo.setRequestData(new CopyLogBo(templateRuleId, ruleInfoVo));

            ruleOperationLogBizService.optLog(operationLogBo);
        }
        // ?????? ???????????????
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
        // ????????????
        boolean validTenantId = TenantIdUtil.validTenantId(tenantId, CurrentReqInfoUtil.currentTenantId(), currentUser);
        if (!validTenantId) {
            log.info("tenantInit ???????????????-reqTenantId={}, currentTenant={}", tenantId, CurrentReqInfoUtil.currentTenantId());
            return ResponseHelper.buildFail(ResponseCodeEnum.TENANT_ID_IS_ERROR);
        }
        RuleTenantFromTemplate ruleTenantFromTemplate = ruleTenantFromTemplateService.getByTenantId(tenantId);
        if (tenantHasInit(tenantId)) {
            // ??????????????? ????????????
            log.info("tenantInit ???????????????-reqTenantId={}, currentTenant={}", tenantId, CurrentReqInfoUtil.currentTenantId());
            ruleTenantFromTemplate.setIsCopy(CommonStatusEnum.STATUS_NOT_ENABLED.getStatus());
            ruleTenantFromTemplateService.updateById(ruleTenantFromTemplate);

            // ?????????????????????????????????????????????
            List<RuleInfo> copyFromTemplateRules = ruleInfoService.queryTenantInitFromTemplate(tenantId);
            if (CollectionUtils.isNotEmpty(copyFromTemplateRules)) {
                List<Long> copyFromTemplateRuleIds = copyFromTemplateRules.stream().map(RuleInfo::getId).collect(Collectors.toList());
                ruleInfoService.deleteByRuleIds(copyFromTemplateRuleIds);
                // ?????? ?????????????????? ??????
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
            // ?????? ?????????????????? ??????
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
                log.info("updateRule_length_between_with_both ????????????????????????????????????????????????-ruleCondition={}", ruleCondition);
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
     * ??????????????????
     *
     * @param tenantId
     * @return true ????????????????????????
     * false?????????????????????
     */
    public boolean tenantHasInit(String tenantId) {
        if (StringUtils.isBlank(tenantId)) {
            // ?????? ?????????
            return true;
        }
        RuleTenantFromTemplate ruleTenantFromTemplate = ruleTenantFromTemplateService.getByTenantId(tenantId);
        if (ruleTenantFromTemplate != null && CommonStatusEnum.STATUS_ENABLED.getStatus().equals(ruleTenantFromTemplate.getIsCopy())) {
            // ???????????????
            log.info("[tenantHasInit_{}] ??????????????????, currentTenant={}", tenantId, CurrentReqInfoUtil.currentTenantId());
            return true;
        }
        log.info("[tenantHasInit_{}] ?????????????????????, currentTenant={}", tenantId, CurrentReqInfoUtil.currentTenantId());
        return false;
    }


    /**
     * ??????????????????  & ?????????????????????md5
     *
     * @param vo
     * @return
     */
    private ValidCodeMsg<ResponseCodeEnum> validatePreRuleAndPreMd5(RuleInfoVo vo) {
        ConditionRuleOutJoinerConfig conditionRuleOutJoinerConfig = JSON.parseObject(vo.getPreCondition(), ConditionRuleOutJoinerConfig.class);
        ValidCodeMsg<ResponseCodeEnum> baseValidCodeMsg = RulePreConditionConfigBaseValidator.validConditionBase(conditionRuleOutJoinerConfig);
        if (baseValidCodeMsg.isErr()) {
            log.info("saveOrUpdate ???????????????????????????-preCondition={}", vo.getPreCondition());
            return ValidCodeMsg.error(baseValidCodeMsg.getData());
        }
        for (ConditionRuleFieldKeyConfig fieldKeyConfig : conditionRuleOutJoinerConfig.getConfigs()) {
            for (ConditionRuleFieldValueConfig valueConfig : fieldKeyConfig.getConfigs()) {
                ValidCodeMsgTuple<ResponseCodeEnum, String> validatorInstanceType = RuleConditionValidatorHandler.getValidatorInstanceType(valueConfig.getCondition());
                if (validatorInstanceType.isErr()) {
                    log.info("saveOrUpdate ???????????????????????????????????????????????????-baseRuleConditionValueBo={}", JSON.toJSONString(valueConfig));
                    return ValidCodeMsg.error(validatorInstanceType.getError());
                }
                IRuleConditionParamValueValidator ruleConditionParamValueValidator = ruleConditionValidatorMap.get(validatorInstanceType.getData());
                ValidCodeMsg<ResponseCodeEnum> validate = ruleConditionParamValueValidator.validate(valueConfig.getConfigs());
                if (validate.isErr()) {
                    log.info("saveOrUpdate ???????????????????????????-preCondition={}", vo.getPreCondition());
                    return ValidCodeMsg.error(validate.getData());
                }
                // ??????????????????md5 ???(????????????????????????????????? )
                /*
                     "configs": [
                         "KR","CN"
                     ]
                 */
                String valueMd5 = ruleConditionParamValueValidator.getValueMd5(valueConfig.getConfigs());
                valueConfig.setUnitConfigsValueMd5(valueMd5);
            }
        }

        // ????????????md5 ??????
        vo.setPreConditionMd5(ConditionUtil.getPreConditionMd5(conditionRuleOutJoinerConfig));

        return ValidCodeMsg.success();
    }

    /**
     * ??????????????????
     * 1.??????????????????
     * 2.??????condition  isBlank=true???   isBlank=false??????null????????????
     *
     * @param baseRuleConditionValueBos
     * @return
     */
    private ValidCodeMsg<ResponseCodeEnum> validateBaseRule(List<BaseRuleConditionValueBo> baseRuleConditionValueBos) {
        // ??????????????? = false(????????? ????????????)
        boolean allBlank = true;
        for (BaseRuleConditionValueBo baseRuleConditionValueBo : baseRuleConditionValueBos) {
            if (baseRuleConditionValueBo.confIsBlank()) {
                log.info("saveOrUpdate ???????????????[{}]??????-baseRuleConditionValueBo={}", baseRuleConditionValueBo.getInputKey(), JSON.toJSONString(baseRuleConditionValueBo));
                continue;
            }
            // ?????????????????????
            allBlank = false;

            ValidCodeMsgTuple<ResponseCodeEnum, String> validatorInstanceType = RuleConditionValidatorHandler.getValidatorInstanceType(baseRuleConditionValueBo.getCondition());
            if (validatorInstanceType.isErr()) {
                log.info("saveOrUpdate ????????????????????????????????????????????????????????????????????????-baseRuleConditionValueBo={}", JSON.toJSONString(baseRuleConditionValueBo));
                return ValidCodeMsg.error(ResponseCodeEnum.BASE_RULE_CONDITION_IS_ERROR);
            }
            IRuleConditionParamValueValidator ruleConditionParamValueValidator = ruleConditionValidatorMap.get(validatorInstanceType.getData());
            if (ruleConditionParamValueValidator == null) {
                log.info("saveOrUpdate ???????????????????????????,????????????????????????????????????-baseRuleConditionValueBo={}", JSON.toJSONString(baseRuleConditionValueBo));
                return ValidCodeMsg.error(ResponseCodeEnum.BASE_RULE_CONDITION_IS_ERROR);
            }
            ValidCodeMsg<ResponseCodeEnum> validate = ruleConditionParamValueValidator.validate(baseRuleConditionValueBo.getConfigs());
            if (validate.isErr()) {
                log.info("saveOrUpdate ???????????????????????????????????????????????????-baseRuleConditionValueBo={}", JSON.toJSONString(baseRuleConditionValueBo));
                return ValidCodeMsg.error(validate.getData());
            }
        }
        if (allBlank) {
            // ????????????????????????
            log.info("saveOrUpdate ??????????????????????????????????????????????????????");
            return ValidCodeMsg.error(ResponseCodeEnum.BASE_RULE_CONDITION_IS_ALL_NULL);
        }
        // ?????????????????? ???  ????????????????????????
        return ValidCodeMsg.success();
    }


}
