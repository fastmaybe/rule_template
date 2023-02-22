package com.sf.saas.bsp.rule.core.service.crud;

import com.sf.saas.bsp.rule.core.dao.entity.RuleTenantFromTemplate;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 租户模板记录 服务类
 * </p>
 *
 * @author 01408885
 * @since 2023-02-03
 */
public interface IRuleTenantFromTemplateService extends IService<RuleTenantFromTemplate> {

    RuleTenantFromTemplate getByTenantId(String tenantId);

    void updateRuleTemplate(RuleTenantFromTemplate ruleTenantFromTemplate, String tenantId, Integer isCopy);

    void deleteByTenantId(String tenantId);

    void tenantTemplateSetNotCopy(String tenantId);
}
