package com.sf.saas.bsp.rule.core.service.utils;

import com.sf.saas.bsp.rule.core.common.constans.BspRuleConstant;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author 01407460
 * @date 2022/9/7 16:11
 */
public class CurrentReqInfoUtil {

    private CurrentReqInfoUtil(){}


    public static final ThreadLocal<Map<String, String>> THREAD_AUTH = new ThreadLocal<>();

    public static String currentUser() {
        Map<String, String> map = THREAD_AUTH.get();
        if (null != map) {
            return map.get(BspRuleConstant.HEADER_USER);
        }
        return null;
    }

    public static String currentLang() {
        Map<String, String> map = THREAD_AUTH.get();
        if (null != map) {
            return map.get(BspRuleConstant.HEADER_LAN);
        }
        return null;
    }
    public static String currentTenantId() {
        Map<String, String> map = THREAD_AUTH.get();
        if (null != map) {
            String tenantId = map.get(BspRuleConstant.HEADER_TENANT_ID2);
            if (StringUtils.isBlank(tenantId)) {
                tenantId = map.get(BspRuleConstant.HEADER_TENANT_ID);
            }
            return tenantId;
        }
        return null;
    }
}
