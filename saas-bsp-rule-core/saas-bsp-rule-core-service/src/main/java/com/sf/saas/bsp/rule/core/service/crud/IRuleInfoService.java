package com.sf.saas.bsp.rule.core.service.crud;

import com.sf.saas.bsp.rule.core.dao.entity.RuleInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sf.saas.bsp.rule.core.dto.base.WebPage;
import com.sf.saas.bsp.rule.core.dto.req.LoadOrderPageBaseRuleQueryVo;
import com.sf.saas.bsp.rule.core.dto.req.RuleVoQueryReq;
import com.sf.saas.bsp.rule.core.dto.vo.RuleInfoVo;
import com.sf.saas.bsp.rule.core.service.rule.bo.RuleConfPool;

import java.util.List;


/**
 * <p>
 * 字段规则配置 服务类
 * </p>
 *
 * @author 01408885
 * @since 2022-10-27
 */
public interface IRuleInfoService extends IService<RuleInfo> {

    WebPage<RuleInfoVo> queryPage(RuleVoQueryReq query);
    WebPage<RuleInfoVo> queryPageXml(RuleVoQueryReq query);

    /**
     * 唯一性 判断
     * @param vo
     * @param id
     * @return true-唯一 可以添加；false-不能添加 重复
     */
    RuleInfo uniquenessCheck(RuleInfoVo vo, Long id);

    /**
     * 回去租户下规则
     * @param tenantId
     * @return
     */
    RuleConfPool getRulePool(String tenantId);

    /**
     * 查询前端基础规则
     * 是基础规则  且 webFieldName 不为空
     * @param loadOrderPageBaseRuleQueryVo
     * @return
     */
    List<RuleInfoVo> loadWebBaseRule(LoadOrderPageBaseRuleQueryVo loadOrderPageBaseRuleQueryVo);

    /**
     * 查询所有模板规则
     * @return
     */
    List<RuleInfoVo> selectTemplateList();

    void deleteByTenantId(String tenantId);

    /**
     * 查询从模板复制来的规则
     * @param tenantId
     * @return
     */
    List<RuleInfo> queryTenantInitFromTemplate(String tenantId);

    void deleteByRuleIds(List<Long> ruleIds);

    List<RuleInfo> selectByTenantId(String tenantId);
}
