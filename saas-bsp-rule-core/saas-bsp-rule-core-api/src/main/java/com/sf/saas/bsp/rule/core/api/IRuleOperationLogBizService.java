package com.sf.saas.bsp.rule.core.api;

import com.sf.saas.bsp.rule.core.dto.base.WebPage;
import com.sf.saas.bsp.rule.core.dto.base.Response;
import com.sf.saas.bsp.rule.core.dto.bo.OperationLogBo;
import com.sf.saas.bsp.rule.core.dto.req.LogQueryPageVo;
import com.sf.saas.bsp.rule.core.dto.vo.RuleOperationLogVo;

/**
 * Description IRuleOperationLogService
 *
 * @author suntao(01408885)
 * @since 2022-11-02
 */
public interface IRuleOperationLogBizService {
    /**
     * 日志记录
     * @param operationLogBo
     */
    void optLog(OperationLogBo operationLogBo);

    /**
     * 日志分页查询
     * @param queryPageVo
     * @return
     */
    Response<WebPage<RuleOperationLogVo>> queryRuleLogsPage(LogQueryPageVo queryPageVo);
}
