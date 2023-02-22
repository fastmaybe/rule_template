package com.sf.saas.bsp.rule.core.service.config.spring;

import com.sf.saas.bsp.rule.core.service.config.interceptor.RequestAuthInterceptor;
import com.sf.saas.bsp.rule.core.service.config.interceptor.RequestLogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author 01407460
 * @date 2022/9/7 16:41
 */
@Configuration
public class BpsWebMvcConfigurer implements WebMvcConfigurer {

    @Resource
    private RequestLogInterceptor requestLogInterceptor;

    @Resource
    private RequestAuthInterceptor requestAuthInterceptor;



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截乐优先级 针对加入顺序控制
        //order(-1) 被FlowDyeingConfig 使用了
        registry.addInterceptor(requestLogInterceptor).order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/v2/api-docs/**")
                .excludePathPatterns("/error")
        ;
        registry.addInterceptor(requestAuthInterceptor).order(2)
                .addPathPatterns("/manage/**")
                .excludePathPatterns("/base/rule/**")
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/v2/api-docs/**")
                .excludePathPatterns("/error")
        ;
    }
}
