package com.sf.saas.bsp.rule.core.api;

import com.sf.saas.bsp.rule.core.dto.base.WebPage;
import com.sf.saas.bsp.rule.core.dto.base.Response;
import com.sf.saas.bsp.rule.core.dto.req.LoadOrderPageBaseRuleQueryVo;
import com.sf.saas.bsp.rule.core.dto.req.LogQueryPageVo;
import com.sf.saas.bsp.rule.core.dto.req.RuleVoQueryReq;
import com.sf.saas.bsp.rule.core.dto.res.LoadOrderPageBaseRuleQueryResp;
import com.sf.saas.bsp.rule.core.dto.vo.RuleInfoVo;
import com.sf.saas.bsp.rule.core.dto.vo.RuleOperationLogVo;

/**
 * Description IRuleInfoBizService
 *
 * @author suntao(01408885)
 * @since 2022-10-26
 */
public interface IRuleInfoBizService {

    /**
     * 分页查询
     * @param query
     * @return
     */
    WebPage<RuleInfoVo> queryPage(RuleVoQueryReq query);

    /**
     * 新增或者修改
     * @param vo
     * @return
     */
    Response<String> saveOrUpdate(RuleInfoVo vo);


    /**
     * 校验有无重复
     * @param vo
     * @return
     */
    Response<String> uniquenessCheck(RuleInfoVo vo);


    Response<String> updateStatus(RuleInfoVo vo);

    /**
     * 下单页面拉取基础规则
     * @param loadOrderPageBaseRuleQueryVo
     * @return
     */
    Response<LoadOrderPageBaseRuleQueryResp> loadWebBaseRule(LoadOrderPageBaseRuleQueryVo loadOrderPageBaseRuleQueryVo);

    /**
     * 初始化租户模板规则
     * @param tenantId
     * @return
     */
    Response tenantInit(String tenantId);

    /**
     * 强制再次初始化 从模板
     * @param tenantId
     * @return
     */
    Response forceTenantInit(String tenantId);

    /**
     * 后门删除rule
     * @param ruleIds
     * @return
     */
    Response adminDeleteRuleIds(String ruleIds);

    /**
     * 删除租户下全部规则
     * @param tenantId
     * @return
     */
    Response deleteByTenantId(String tenantId);

    void updateRule_length_between_with_both(String tenantId);
}
