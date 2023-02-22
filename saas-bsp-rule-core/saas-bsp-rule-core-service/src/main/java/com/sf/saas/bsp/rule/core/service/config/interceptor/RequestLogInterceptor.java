package com.sf.saas.bsp.rule.core.service.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.sf.saas.bsp.rule.core.common.constans.BspRuleConstant;
import com.sf.saas.bsp.rule.core.service.utils.CurrentReqInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 01407460
 * @date 2022/9/7 15:35
 */
@Slf4j
@Component
public class RequestLogInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        initCurrentReqInfo(request);

        String reqUrl = request.getRequestURI();
        String method = request.getMethod();
        String headerParams = logHeaderParams(request);

        if (RequestMethod.GET.toString().equals(method)){
            String getParams = logGetParams(request);
            log.info("request GET url={},headers={},getParams={}",reqUrl,headerParams,getParams);
        } else if (RequestMethod.POST.toString().equals(method)){
            log.info("request POST url={},headers={}",reqUrl,headerParams);
        }

        return Boolean.TRUE;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        removeCurrentReqInfo();
    }

    private void initCurrentReqInfo(HttpServletRequest request) {
        String language = request.getHeader(BspRuleConstant.HEADER_LAN);
        String username = request.getHeader(BspRuleConstant.HEADER_USER);
        String tenantId = request.getHeader(BspRuleConstant.HEADER_TENANT_ID);
        String tenantId2 = request.getHeader(BspRuleConstant.HEADER_TENANT_ID2);


        if (StringUtils.isBlank(language)){
            language = BspRuleConstant.DEFAULT_LANG;
        }

        Map<String, String> infoMap = new HashMap<>(8);
        infoMap.put(BspRuleConstant.HEADER_LAN, language);
        infoMap.put(BspRuleConstant.HEADER_USER, username);
        infoMap.put(BspRuleConstant.HEADER_TENANT_ID,tenantId);
        infoMap.put(BspRuleConstant.HEADER_TENANT_ID2,tenantId2);
        CurrentReqInfoUtil.THREAD_AUTH.set(infoMap);
    }



    private void removeCurrentReqInfo() {
        CurrentReqInfoUtil.THREAD_AUTH.remove();
    }


    private String logGetParams(HttpServletRequest request) {
       return JSON.toJSONString(request.getParameterMap());
    }
    private String logHeaderParams(HttpServletRequest request) {
       return getHeaderParamString(request);
    }

    private String getHeaderParamString(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        HashMap<String, String> hashMap = new HashMap<>(8);
        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            hashMap.put(headerName,request.getHeader(headerName));
        }
        return JSON.toJSONString(hashMap);
    }


}
