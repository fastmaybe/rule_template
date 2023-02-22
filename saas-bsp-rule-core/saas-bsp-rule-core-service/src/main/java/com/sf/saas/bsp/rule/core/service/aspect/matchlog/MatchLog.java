package com.sf.saas.bsp.rule.core.service.aspect.matchlog;

import com.sf.saas.bsp.rule.core.common.emnus.RequestTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 01407460
 * @date 2022/9/20 14:22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MatchLog {

    RequestTypeEnum value() default RequestTypeEnum.CONTROL;

}
