package com.sf.saas.bsp.rule.core.service.crud;

import com.sf.saas.bsp.rule.core.dao.entity.RuleTipContent;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 规则多语言响应提示 服务类
 * </p>
 *
 * @author 01408885
 * @since 2023-02-02
 */
public interface IRuleTipContentService extends IService<RuleTipContent> {

    void deleteByRuleId(Long id);

    void deleteByRuleIds(List<Long> ruleIds);

    void deleteByTenantId(String tenantId);

    List<RuleTipContent> queryByRuleId(Long ruleLongId);
}
