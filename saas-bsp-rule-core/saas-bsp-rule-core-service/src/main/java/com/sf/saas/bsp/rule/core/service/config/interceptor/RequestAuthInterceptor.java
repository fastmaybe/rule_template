package com.sf.saas.bsp.rule.core.service.config.interceptor;

import com.sf.saas.bsp.rule.core.common.constans.ResponseCodeEnum;
import com.sf.saas.bsp.rule.core.common.exception.BizException;
import com.sf.saas.bsp.rule.core.service.utils.CurrentReqInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 01407460
 * @date 2022/9/7 16:45
 */
@Slf4j
@Component
public class RequestAuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = CurrentReqInfoUtil.currentUser();
        String tenantId = CurrentReqInfoUtil.currentTenantId();

        if (StringUtils.isBlank(username)) {
            throw new BizException(ResponseCodeEnum.USERNAME_IS_NULL);
        }
        if (StringUtils.isBlank(tenantId)) {
            throw new BizException(ResponseCodeEnum.TENANT_ID_IS_NULL);
        }

        return true;
    }


}
