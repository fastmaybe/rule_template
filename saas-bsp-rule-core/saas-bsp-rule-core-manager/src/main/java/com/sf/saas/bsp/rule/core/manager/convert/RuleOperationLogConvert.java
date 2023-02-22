package com.sf.saas.bsp.rule.core.manager.convert;

import com.alibaba.fastjson.JSON;
import com.sf.saas.bsp.rule.core.dao.entity.RuleOperationLog;
import com.sf.saas.bsp.rule.core.dto.bo.OperationLogBo;
import com.sf.saas.bsp.rule.core.dto.enums.OperationTypeEnum;
import com.sf.saas.bsp.rule.core.dto.vo.RuleOperationLogVo;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @author suntao(01408885)
 * @since 2022-11-02
 */
public class RuleOperationLogConvert {
    public static RuleOperationLog convertBo2Do(OperationLogBo operationLogBo) {
        if (operationLogBo == null) {
            return null;
        }
        RuleOperationLog log = new RuleOperationLog();
        log.setTenantId(operationLogBo.getTenantId());
        log.setRuleId(operationLogBo.getRuleId());
        if (operationLogBo.getTypeEnum() != null) {
            log.setOperationType(operationLogBo.getTypeEnum().getType());
        }
        log.setOperationData(JSON.toJSONString(operationLogBo.getRequestData()));
        log.setOperatorId(operationLogBo.getUser());
        log.setOperatorName(operationLogBo.getUser());
        log.setGmtCreate(operationLogBo.getGmtModified());
        return log;
    }
    public static RuleOperationLogVo convertDo2Vo(RuleOperationLog ruleOperationLog) {
        if (ruleOperationLog == null) {
            return null;
        }
        RuleOperationLogVo log = new RuleOperationLogVo();
        log.setUser(ruleOperationLog.getOperatorId());
        log.setTenantId(ruleOperationLog.getTenantId());
        log.setRuleId(ruleOperationLog.getRuleId());
        log.setGmtModified(ruleOperationLog.getGmtCreate());

        log.setOperationType(ruleOperationLog.getOperationType()+"");


        log.setRequestData(ruleOperationLog.getOperationData());
        return log;
    }

    public static List<RuleOperationLogVo> convertDo2VoList(List<RuleOperationLog> records) {
        return records.stream().map(RuleOperationLogConvert::convertDo2Vo).collect(Collectors.toList());

    }
}
