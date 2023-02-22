package com.sf.saas.bsp.rule.core.service.utils;

import com.sf.saas.bsp.rule.core.common.constans.CommonStatusEnum;
import com.sf.saas.bsp.rule.core.dto.enums.OperationTypeEnum;
import lombok.extern.log4j.Log4j2;

/**
 * Description RuleOperationUtil
 *
 * @author suntao(01408885)
 * @since 2022-11-03
 */
@Log4j2
public class RuleOperationUtil {

    public static OperationTypeEnum getNewOrUpdateType(boolean isNew) {
        if (isNew) {
            return OperationTypeEnum.NEW;
        } else {
            return OperationTypeEnum.UPDATE;
        }
    }

    /**
     * 无效状态 0
     * 有效状态 1
     * @param sourceState
     * @param targetState
     * @return
     */
    public static OperationTypeEnum getStateType(Integer sourceState, Integer targetState) {
        if (sourceState.equals(targetState)) {
            log.info("目标状态为空");
            return null;
        }
        if (CommonStatusEnum.STATUS_NOT_ENABLED.getStatus().equals(sourceState) && CommonStatusEnum.STATUS_ENABLED.getStatus().equals(targetState)) {
            // 启用
            return OperationTypeEnum.ENABLE;
        }
        if (CommonStatusEnum.STATUS_ENABLED.getStatus().equals(sourceState) && CommonStatusEnum.STATUS_NOT_ENABLED.getStatus().equals(targetState)) {
            // 停用
            return OperationTypeEnum.DEACTIVATE;
        }
        log.info("状态值非法");
        return null;
    }
}
