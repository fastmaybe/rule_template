package com.sf.saas.bsp.rule.core.common.utils;

/**
 * @author 01407460
 * @date 2022/9/16 11:12
 */
public class TenantUtil {

    public static final String TENANT_ID = "SF";

    private TenantUtil() {
    }

    public static boolean isSfTenant(String tenantId) {
        return TENANT_ID.equals(tenantId);
    }
}
