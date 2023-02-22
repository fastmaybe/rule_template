package com.sf.saas.bsp.rule.core.service.biz;

import com.sf.saas.bsp.rule.core.api.IRuleOperationLogBizService;
import com.sf.saas.bsp.rule.core.common.utils.ResponseHelper;
import com.sf.saas.bsp.rule.core.dao.entity.RuleOperationLog;
import com.sf.saas.bsp.rule.core.dto.base.WebPage;
import com.sf.saas.bsp.rule.core.dto.base.Response;
import com.sf.saas.bsp.rule.core.dto.bo.OperationLogBo;
import com.sf.saas.bsp.rule.core.dto.req.LogQueryPageVo;
import com.sf.saas.bsp.rule.core.dto.vo.RuleOperationLogVo;
import com.sf.saas.bsp.rule.core.manager.convert.RuleOperationLogConvert;
import com.sf.saas.bsp.rule.core.service.crud.IRuleOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description RuleOperationLogServiceImpl
 *
 * @author suntao(01408885)
 * @since 2022-11-02
 */
@Service
public class RuleOperationLogServiceBizImpl implements IRuleOperationLogBizService {

    @Autowired
    private IRuleOperationLogService ruleOperationLogService;

    @Override
    public void optLog(OperationLogBo operationLogBo) {

        RuleOperationLog ruleOperationLog = RuleOperationLogConvert.convertBo2Do(operationLogBo);
        ruleOperationLogService.save(ruleOperationLog);

    }

    @Override
    public Response<WebPage<RuleOperationLogVo>> queryRuleLogsPage(LogQueryPageVo queryPageVo) {
        return ResponseHelper.buildSuccess(ruleOperationLogService.queryPage(queryPageVo));
    }
}
