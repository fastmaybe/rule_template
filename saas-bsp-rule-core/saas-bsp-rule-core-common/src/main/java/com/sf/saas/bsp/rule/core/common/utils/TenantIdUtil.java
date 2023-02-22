package com.sf.saas.bsp.rule.core.common.utils;

import com.sf.saas.bsp.rule.core.common.constans.BspRuleConstant;
import com.sf.saas.bsp.rule.core.common.constans.CommonStatusEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * Description TenantIdUtil
 *
 * @author suntao(01408885)
 * @since 2022-09-14
 * return: true-方形；false-不允许操作
 */
public class TenantIdUtil {

    public static boolean validTenantId(String reqTenantId, String currentTenantId, String currentUser) {

        if (!StringUtils.equals(reqTenantId, currentTenantId)) {
            if (BspRuleConstant.ADMIN_USER.equals(currentUser)) {
                return true;
            }
            return false;
        }
        return true;
    }

    public static boolean validTenantId(String reqTenantId, String currentTenantId, String currentUser, Integer ruleTemplate) {

        if (reqTenantId == null && CommonStatusEnum.STATUS_ENABLED.getStatus().equals(ruleTemplate)) {
            // 模板规则没有租户
            return true;
        }

        if (!StringUtils.equals(reqTenantId, currentTenantId)) {
            if (BspRuleConstant.ADMIN_USER.equals(currentUser)) {
                return true;
            }
            return false;
        }
        return true;
    }
}
