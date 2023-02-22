package com.sf.saas.bsp.rule.core.service.crud.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sf.saas.bsp.rule.core.common.constans.CommonStatusEnum;
import com.sf.saas.bsp.rule.core.dao.entity.RuleInfo;
import com.sf.saas.bsp.rule.core.dao.mapper.RuleInfoMapper;
import com.sf.saas.bsp.rule.core.dto.base.WebPage;
import com.sf.saas.bsp.rule.core.dto.req.LoadOrderPageBaseRuleQueryVo;
import com.sf.saas.bsp.rule.core.dto.req.RuleVoQueryReq;
import com.sf.saas.bsp.rule.core.dto.vo.RuleInfoVo;
import com.sf.saas.bsp.rule.core.manager.convert.RuleInfoConvert;
import com.sf.saas.bsp.rule.core.service.crud.IRuleInfoService;
import com.sf.saas.bsp.rule.core.service.rule.biz.RuleUtil;
import com.sf.saas.bsp.rule.core.service.rule.bo.RuleConfPool;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 字段规则配置 服务实现类
 * </p>
 *
 * @author 01408885
 * @since 2022-10-27
 */
@Log4j2
@Service
public class RuleInfoServiceImpl extends ServiceImpl<RuleInfoMapper, RuleInfo> implements IRuleInfoService {

    @Autowired
    private RuleInfoMapper ruleInfoMapper;

    @Override
    public WebPage<RuleInfoVo> queryPage(RuleVoQueryReq query) {


        LambdaQueryWrapper<RuleInfo> wrapper = Wrappers.<RuleInfo>lambdaQuery()
                .eq(RuleInfo::getTenantId, query.getTenantId())
                .eq(query.getState() != null, RuleInfo::getState, query.getState())
                .eq(query.getRuleType() != null, RuleInfo::getRuleType, query.getRuleType())
                .like(StringUtils.isNotBlank(query.getConfigName()), RuleInfo::getConfigName, query.getConfigName())
                .like(StringUtils.isNotBlank(query.getFieldKey()), RuleInfo::getFieldKey, query.getFieldKey())
                .orderByDesc(RuleInfo::getGmtCreate);

        Integer selectCount = baseMapper.selectCount(wrapper);

        Page<RuleInfo> mybatisPage = new Page(query.getPageNum(), query.getPageSize());

        Page<RuleInfo> selectPage = baseMapper.selectPage(mybatisPage, wrapper);

        List<RuleInfoVo> ruleInfoVoList = RuleInfoConvert.convertDo2VoList(selectPage.getRecords());


        return new WebPage<>(query.getPageNum(), query.getPageSize(), Long.valueOf(selectCount), ruleInfoVoList);
    }

    @Override
    public WebPage<RuleInfoVo> queryPageXml(RuleVoQueryReq query) {
        // 查询模板是 查询参数中tenant要设置为空
        if (CommonStatusEnum.STATUS_ENABLED.getStatus().equals(query.getRuleTemplate())) {
            query.setTenantId(null);
        }

        LambdaQueryWrapper<RuleInfo> wrapper = Wrappers.<RuleInfo>lambdaQuery()
                .eq(RuleInfo::getTenantId, query.getTenantId())
                .and(item->item.eq(StringUtils.isNotBlank(query.getTenantId()), RuleInfo::getTenantId, query.getTenantId())
                        .or()
                        .isNull(StringUtils.isBlank(query.getTenantId()), RuleInfo::getTenantId))
                .eq(query.getState() != null, RuleInfo::getState, query.getState())
                .eq(query.getRuleType() != null, RuleInfo::getRuleType, query.getRuleType())
                .eq(query.getRuleTemplate() != null, RuleInfo::getRuleTemplate, query.getRuleTemplate())
                .like(StringUtils.isNotBlank(query.getConfigName()), RuleInfo::getConfigName, query.getConfigName())
                .like(StringUtils.isNotBlank(query.getFieldKey()), RuleInfo::getFieldKey, query.getFieldKey())
                .orderByDesc(RuleInfo::getGmtCreate);

        Integer selectCount = ruleInfoMapper.selectByParamCount(query);
        List<RuleInfoVo> ruleInfos;
        if (selectCount > 0) {
            ruleInfos = ruleInfoMapper.selectByParam(query);
        } else {
            ruleInfos = Collections.EMPTY_LIST;
        }
        return new WebPage<>(query.getPageNum(), query.getPageSize(), Long.valueOf(selectCount), ruleInfos);
    }

    @Override
    public RuleInfo uniquenessCheck(RuleInfoVo vo, Long id) {
        // 下单方式 + 规则类型 + 前置条件 + 字段不能重复
        LambdaQueryWrapper<RuleInfo> wrapper;
        if (StringUtils.isBlank(vo.getTenantId())) {
            wrapper = Wrappers.<RuleInfo>lambdaQuery()
                    .isNull(RuleInfo::getTenantId)
                    .eq(RuleInfo::getOrderChannel, vo.getOrderChannel())
                    .eq(RuleInfo::getRuleType, vo.getRuleType())
                    .eq(StringUtils.isNotBlank(vo.getPreConditionMd5()), RuleInfo::getPreConditionMd5, vo.getPreConditionMd5())
                    .and(item->item.eq(StringUtils.isNotBlank(vo.getFieldKey()), RuleInfo::getFieldKey, vo.getFieldKey())
                            .or()
                            .eq(StringUtils.isNotBlank(vo.getWebFieldName()), RuleInfo::getWebFieldName, vo.getWebFieldName()))
                    .ne(id != null, RuleInfo::getId, id);
        } else {
            wrapper = Wrappers.<RuleInfo>lambdaQuery()
                    .eq(RuleInfo::getTenantId, vo.getTenantId())
                    .eq(RuleInfo::getOrderChannel, vo.getOrderChannel())
                    .eq(RuleInfo::getRuleType, vo.getRuleType())
                    .eq(StringUtils.isNotBlank(vo.getPreConditionMd5()), RuleInfo::getPreConditionMd5, vo.getPreConditionMd5())
                    .and(item->item.eq(StringUtils.isNotBlank(vo.getFieldKey()), RuleInfo::getFieldKey, vo.getFieldKey())
                            .or()
                            .eq(StringUtils.isNotBlank(vo.getWebFieldName()), RuleInfo::getWebFieldName, vo.getWebFieldName()))
                    .ne(id != null, RuleInfo::getId, id);
        }
        List<RuleInfo> ruleInfos = baseMapper.selectList(wrapper);
        if (CollectionUtils.isNotEmpty(ruleInfos)) {
            RuleInfo ruleInfo = ruleInfos.get(0);
            log.info("[uniquenessCheck_repeat_id]存在重复数据id={}, 重复ids={}", ruleInfo.getId(), JSON.toJSONString(ruleInfos.stream().map(RuleInfo::getId).collect(Collectors.toList())));
            return ruleInfo;
        }
        // true-唯一 可以添加；false-重复
        return null;
    }


    /**
     * 获取后端 校验规则
     *
     * @param tenantId
     * @return
     */
    @Override
    public RuleConfPool getRulePool(String tenantId) {


        List<RuleInfoVo> ruleInfoVos = ruleInfoMapper.selectCheckRulePool(tenantId, CommonStatusEnum.STATUS_ENABLED.getStatus());


        // 理论上  通过租户 拿到不可能是模板
        List<RuleInfoVo> collect = ruleInfoVos.stream()
                //0 -》不是模板  1 是模板
                .filter(e -> !CommonStatusEnum.STATUS_ENABLED.getStatus().equals(e.getRuleTemplate()))
                .filter(e -> StringUtils.isNotBlank(e.getFieldKey()))
                .collect(Collectors.toList());


        return RuleUtil.buildPool(collect);
    }

    @Override
    public List<RuleInfoVo> loadWebBaseRule(LoadOrderPageBaseRuleQueryVo loadOrderPageBaseRuleQueryVo) {
        return ruleInfoMapper.loadWebBaseRule(loadOrderPageBaseRuleQueryVo);
    }

    @Override
    public List<RuleInfoVo> selectTemplateList() {
        return ruleInfoMapper.selectTemplateList();
    }

    @Override
    public void deleteByTenantId(String tenantId) {
        if (StringUtils.isBlank(tenantId)) {
            return;
        }
        LambdaQueryWrapper<RuleInfo> wrapper = Wrappers.<RuleInfo>lambdaQuery()
                .eq(RuleInfo::getTenantId, tenantId);
        baseMapper.delete(wrapper);
    }

    @Override
    public List<RuleInfo> queryTenantInitFromTemplate(String tenantId) {
        if (StringUtils.isBlank(tenantId)) {
            return Collections.EMPTY_LIST;
        }
        LambdaQueryWrapper<RuleInfo> wrapper = Wrappers.<RuleInfo>lambdaQuery()
                .eq(RuleInfo::getTenantId, tenantId)
                .eq(RuleInfo::getFromTemplate, CommonStatusEnum.STATUS_ENABLED.getStatus());
        return baseMapper.selectList(wrapper);
    }

    @Override
    public void deleteByRuleIds(List<Long> ruleIds) {
        if (CollectionUtils.isEmpty(ruleIds)) {
            return ;
        }
        LambdaQueryWrapper<RuleInfo> wrapper = Wrappers.<RuleInfo>lambdaQuery()
                .in(RuleInfo::getId, ruleIds);
        baseMapper.delete(wrapper);
    }

    @Override
    public List<RuleInfo> selectByTenantId(String tenantId) {
        LambdaQueryWrapper<RuleInfo> wrapper;
        if (StringUtils.isBlank(tenantId)) {
            wrapper = Wrappers.<RuleInfo>lambdaQuery()
                    .isNull(RuleInfo::getTenantId);
        } else if ("all".equals(tenantId)) {
            wrapper = Wrappers.<RuleInfo>lambdaQuery();
        } else {
            wrapper = Wrappers.<RuleInfo>lambdaQuery()
                    .eq(RuleInfo::getTenantId, tenantId);
        }
        return baseMapper.selectList(wrapper);
    }
}
