package com.sf.saas.bsp.rule.core.manager.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.sf.saas.bsp.rule.core.common.constans.BspRuleConstant;
import com.sf.saas.bsp.rule.core.common.constans.CommonStatusEnum;
import com.sf.saas.bsp.rule.core.common.emnus.BaseRuleFieldNameEnum;
import com.sf.saas.bsp.rule.core.dao.entity.RuleInfo;
import com.sf.saas.bsp.rule.core.dto.base.WebPage;
import com.sf.saas.bsp.rule.core.dto.bo.condition.base.BaseRuleConditionValueBo;
import com.sf.saas.bsp.rule.core.dto.enums.ValueRangeConditionEnum;
import com.sf.saas.bsp.rule.core.dto.res.LoadOrderPageBaseRuleFieldInfo;
import com.sf.saas.bsp.rule.core.dto.res.LoadOrderPageBaseRuleRangInfo;
import com.sf.saas.bsp.rule.core.dto.vo.RuleInfoVo;
import com.sf.saas.bsp.rule.core.dto.vo.WebRuleTipContentVo;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * Description DictionaryConvert
 *
 * @author suntao(01408885)
 * @since 2022-09-07
 */
@Log4j2
public class RuleInfoConvert {


    public static RuleInfo convertRuleInfoVo2Do(RuleInfoVo vo) {
        if (vo == null) {
            return null;
        }
        RuleInfo ruleInfo = new RuleInfo();
        ruleInfo.setId(vo.getId());
        ruleInfo.setTenantId(vo.getTenantId());
        ruleInfo.setConfigName(vo.getConfigName());
        ruleInfo.setFieldKey(vo.getFieldKey());
        ruleInfo.setWebFieldName(vo.getWebFieldName());
        ruleInfo.setRemark(vo.getRemark());
        ruleInfo.setOrderChannel(vo.getOrderChannel());
        ruleInfo.setRuleType(vo.getRuleType());

        ruleInfo.setMonthCardNoWhite(vo.getMonthCardNoWhite());
        ruleInfo.setRuleTemplate(vo.getRuleTemplate());
        // 页面add 不是来自模板
        // ruleInfo.setFromTemplate(CommonStatusEnum.STATUS_NOT_ENABLED.getStatus());
        ruleInfo.setPreCondition(vo.getPreCondition());
        ruleInfo.setPreConditionMd5(vo.getPreConditionMd5());
        ruleInfo.setRuleCondition(vo.getRuleCondition());
        ruleInfo.setRuleConditionBackstage(vo.getRuleConditionBackstage());

        if (vo.getGmtCreate() == null) {
            ruleInfo.setGmtCreate(System.currentTimeMillis());
        } else {
            ruleInfo.setGmtCreate(vo.getGmtCreate());
        }
        if (StringUtils.isBlank(vo.getCreateBy())) {
            ruleInfo.setCreateBy(BspRuleConstant.EDIT_BY_SYSTEM);
        } else {
            ruleInfo.setCreateBy(vo.getCreateBy());
        }
        ruleInfo.setWebDisplay(vo.getWebDisplay());
        ruleInfo.setWebDisplayOrder(vo.getWebDisplayOrder());
        ruleInfo.setState(vo.getState());
        ruleInfo.setGmtModified(vo.getGmtModified());
        ruleInfo.setModifiedBy(vo.getModifiedBy());
        return ruleInfo;
    }

    public static RuleInfoVo convertRuleDo2Vo(RuleInfo ruleInfo) {
        if (ruleInfo == null) {
            return null;
        }
        RuleInfoVo vo = new RuleInfoVo();
        vo.setId(ruleInfo.getId());
        vo.setTenantId(ruleInfo.getTenantId());
        vo.setConfigName(ruleInfo.getConfigName());
        vo.setFieldKey(ruleInfo.getFieldKey());
        vo.setWebFieldName(ruleInfo.getWebFieldName());
        vo.setState(ruleInfo.getState());
        vo.setRemark(ruleInfo.getRemark());
        vo.setOrderChannel(ruleInfo.getOrderChannel());
        vo.setRuleType(ruleInfo.getRuleType());
        vo.setConfigKey(ruleInfo.getConfigKey());
        vo.setRuleCondition(ruleInfo.getRuleCondition());
        vo.setRuleConditionBackstage(ruleInfo.getRuleConditionBackstage());
        vo.setPreCondition(ruleInfo.getPreCondition());
        vo.setPreConditionMd5(ruleInfo.getPreConditionMd5());
        vo.setMonthCardNoWhite(ruleInfo.getMonthCardNoWhite());
        vo.setGmtCreate(ruleInfo.getGmtCreate());
        vo.setCreateBy(ruleInfo.getCreateBy());
        vo.setGmtModified(ruleInfo.getGmtModified());
        vo.setModifiedBy(ruleInfo.getModifiedBy());
        return vo;
    }


    public static List<RuleInfoVo> convertDo2VoList(List<RuleInfo> list) {
        return list.stream().map(RuleInfoConvert::convertRuleDo2Vo).collect(Collectors.toList());
    }

    public static List<LoadOrderPageBaseRuleFieldInfo> convertDo2LoadOrderPageBaseRuleFieldInfo(List<RuleInfo> ruleInfos) {
        if (CollectionUtils.isEmpty(ruleInfos)) {
            return Lists.newArrayList();
        }
        int i0 = BspRuleConstant.i_0;
        int i1 = BspRuleConstant.i_1;

        return ruleInfos.stream().map(it -> {
            LoadOrderPageBaseRuleFieldInfo loadOrderPageBaseRuleFieldInfo = new LoadOrderPageBaseRuleFieldInfo();
            loadOrderPageBaseRuleFieldInfo.setWebFieldName(it.getFieldKey());
            loadOrderPageBaseRuleFieldInfo.setMonthCardNoWhite(it.getMonthCardNoWhite());

            List<BaseRuleConditionValueBo> baseRuleConditionValueBos = JSON.parseArray(it.getRuleCondition(), BaseRuleConditionValueBo.class);
            for (BaseRuleConditionValueBo baseRuleConditionValueBo : baseRuleConditionValueBos) {
                if (baseRuleConditionValueBo.confIsNotBlank()) {
                    try {
                        Object configs = baseRuleConditionValueBo.getConfigs();
                        JSONArray jArray = JSON.parseArray(String.valueOf(configs));

                        switch (Objects.requireNonNull(BaseRuleFieldNameEnum.getByFieldName(baseRuleConditionValueBo.getInputKey()))) {
                            case REQUIRED:
                                loadOrderPageBaseRuleFieldInfo.setRequired(jArray.getBoolean(i0));
                                break;
                            case FIELD_LENGTH:
                                loadOrderPageBaseRuleFieldInfo.setFieldLength(Arrays.asList(jArray.getInteger(i0), jArray.getInteger(i1)));
                                break;
                            case VALUE_RANGE:
                                LoadOrderPageBaseRuleRangInfo loadOrderPageBaseRuleRangInfo = new LoadOrderPageBaseRuleRangInfo();
                                loadOrderPageBaseRuleRangInfo.setCondition(baseRuleConditionValueBo.getCondition());
                                if (ValueRangeConditionEnum.BETWEEN_WITH_BOTH.name().equals(baseRuleConditionValueBo.getCondition())) {
                                    loadOrderPageBaseRuleRangInfo.setConfigs(Arrays.asList(jArray.getDoubleValue(i0), jArray.getDoubleValue(i1)));
                                } else {
                                    loadOrderPageBaseRuleRangInfo.setConfigs(Collections.singletonList(jArray.getDoubleValue(i0)));
                                }
                                loadOrderPageBaseRuleFieldInfo.setRange(loadOrderPageBaseRuleRangInfo);
                                break;
                            case REGEX:
                                loadOrderPageBaseRuleFieldInfo.setRegex(jArray.getString(i0));
                                break;
                        }
                    } catch (Exception e) {
                        log.info("基础条件中的一个元素封装失败：dto={}, e={}", JSON.toJSONString(baseRuleConditionValueBo), ExceptionUtils.getStackTrace(e));
                    }
                }
            }
            return loadOrderPageBaseRuleFieldInfo;
        }).collect(Collectors.toList());
    }

    public static List<LoadOrderPageBaseRuleFieldInfo> convertVo2LoadOrderPageBaseRuleFieldInfo(List<RuleInfoVo> ruleInfoVos) {
        if (CollectionUtils.isEmpty(ruleInfoVos)) {
            return Lists.newArrayList();
        }
        int i0 = BspRuleConstant.i_0;
        int i1 = BspRuleConstant.i_1;

        return ruleInfoVos.stream().map(it -> {
            LoadOrderPageBaseRuleFieldInfo loadOrderPageBaseRuleFieldInfo = new LoadOrderPageBaseRuleFieldInfo();
            loadOrderPageBaseRuleFieldInfo.setWebFieldName(it.getWebFieldName());
            loadOrderPageBaseRuleFieldInfo.setMonthCardNoWhite(it.getMonthCardNoWhite());

            List<BaseRuleConditionValueBo> baseRuleConditionValueBos = JSON.parseArray(it.getRuleCondition(), BaseRuleConditionValueBo.class);
            for (BaseRuleConditionValueBo baseRuleConditionValueBo : baseRuleConditionValueBos) {
                if (baseRuleConditionValueBo.confIsNotBlank()) {
                    try {
                        Object configs = baseRuleConditionValueBo.getConfigs();
                        JSONArray jArray = JSON.parseArray(String.valueOf(configs));

                        switch (Objects.requireNonNull(BaseRuleFieldNameEnum.getByFieldName(baseRuleConditionValueBo.getInputKey()))) {
                            case REQUIRED:
                                loadOrderPageBaseRuleFieldInfo.setRequired(jArray.getBoolean(i0));
                                break;
                            case FIELD_LENGTH:
                                loadOrderPageBaseRuleFieldInfo.setFieldLength(Arrays.asList(jArray.getInteger(i0), jArray.getInteger(i1)));
                                break;
                            case VALUE_RANGE:
                                LoadOrderPageBaseRuleRangInfo loadOrderPageBaseRuleRangInfo = new LoadOrderPageBaseRuleRangInfo();
                                loadOrderPageBaseRuleRangInfo.setCondition(baseRuleConditionValueBo.getCondition());
                                if (ValueRangeConditionEnum.BETWEEN_WITH_BOTH.name().equals(baseRuleConditionValueBo.getCondition())) {
                                    loadOrderPageBaseRuleRangInfo.setConfigs(Arrays.asList(jArray.getDoubleValue(i0), jArray.getDoubleValue(i1)));
                                } else if(ValueRangeConditionEnum.IN_CONF.name().equals(baseRuleConditionValueBo.getCondition())) {
                                    loadOrderPageBaseRuleRangInfo.setConfigs(jArray.toJavaList(Object.class));
                                } else {
                                    loadOrderPageBaseRuleRangInfo.setConfigs(Collections.singletonList(jArray.getDoubleValue(i0)));
                                }
                                loadOrderPageBaseRuleFieldInfo.setRange(loadOrderPageBaseRuleRangInfo);
                                break;
                            case REGEX:
                                loadOrderPageBaseRuleFieldInfo.setRegex(jArray.getString(i0));
                                break;
                        }
                    } catch (Exception e) {
                        log.info("基础条件中的一个元素封装失败：dto={}, e={}", JSON.toJSONString(baseRuleConditionValueBo), ExceptionUtils.getStackTrace(e));
                    }
                }
            }
            List<WebRuleTipContentVo> tips = it.getRuleTipContentVos().stream().map(rtc -> {
                WebRuleTipContentVo vo = new WebRuleTipContentVo();
                BeanUtils.copyProperties(rtc, vo);
                return vo;
            }).collect(Collectors.toList());
            loadOrderPageBaseRuleFieldInfo.setRuleTipContentVos(tips);
            return loadOrderPageBaseRuleFieldInfo;
        }).collect(Collectors.toList());
    }
}
