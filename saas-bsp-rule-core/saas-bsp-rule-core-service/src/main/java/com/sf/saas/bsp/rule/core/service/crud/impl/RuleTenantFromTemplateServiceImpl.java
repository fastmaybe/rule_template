package com.sf.saas.bsp.rule.core.service.crud.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sf.saas.bsp.rule.core.common.constans.BspRuleConstant;
import com.sf.saas.bsp.rule.core.common.constans.CommonStatusEnum;
import com.sf.saas.bsp.rule.core.dao.entity.RuleInfo;
import com.sf.saas.bsp.rule.core.dao.entity.RuleTenantFromTemplate;
import com.sf.saas.bsp.rule.core.dao.mapper.RuleTenantFromTemplateMapper;
import com.sf.saas.bsp.rule.core.service.crud.IRuleTenantFromTemplateService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 租户模板记录 服务实现类
 * </p>
 *
 * @author 01408885
 * @since 2023-02-03
 */
@Service
public class RuleTenantFromTemplateServiceImpl extends ServiceImpl<RuleTenantFromTemplateMapper, RuleTenantFromTemplate> implements IRuleTenantFromTemplateService {

    @Override
    public RuleTenantFromTemplate getByTenantId(String tenantId) {

        LambdaQueryWrapper<RuleTenantFromTemplate> queryWrapper = Wrappers.<RuleTenantFromTemplate>lambdaQuery()
                .eq(RuleTenantFromTemplate::getTenantId, tenantId);

        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public void updateRuleTemplate(RuleTenantFromTemplate ruleTenantFromTemplate, String tenantId, Integer isCopy) {
        if (ruleTenantFromTemplate == null) {
            ruleTenantFromTemplate = new RuleTenantFromTemplate();
            ruleTenantFromTemplate.setCreateBy(BspRuleConstant.EDIT_BY_SYSTEM);
            ruleTenantFromTemplate.setModifiedBy(BspRuleConstant.EDIT_BY_SYSTEM);
            ruleTenantFromTemplate.setGmtCreate(System.currentTimeMillis());
        }
        ruleTenantFromTemplate.setTenantId(tenantId);
        ruleTenantFromTemplate.setIsCopy(isCopy);
        ruleTenantFromTemplate.setGmtModified(System.currentTimeMillis());
        if (ruleTenantFromTemplate.getId() == null) {
            baseMapper.insert(ruleTenantFromTemplate);
        } else {
            baseMapper.updateById(ruleTenantFromTemplate);
        }
    }

    @Override
    public void deleteByTenantId(String tenantId) {
        if (StringUtils.isBlank(tenantId)) {
            return;
        }
        LambdaQueryWrapper<RuleTenantFromTemplate> wrapper = Wrappers.<RuleTenantFromTemplate>lambdaQuery()
                .eq(RuleTenantFromTemplate::getTenantId, tenantId);
        baseMapper.delete(wrapper);
    }

    @Override
    public void tenantTemplateSetNotCopy(String tenantId) {
        RuleTenantFromTemplate ruleTenantFromTemplate = getByTenantId(tenantId);
        if (ruleTenantFromTemplate != null && CommonStatusEnum.STATUS_ENABLED.getStatus().equals(ruleTenantFromTemplate.getIsCopy())) {
            ruleTenantFromTemplate.setIsCopy(CommonStatusEnum.STATUS_NOT_ENABLED.getStatus());
            baseMapper.updateById(ruleTenantFromTemplate);
        }
    }

}
