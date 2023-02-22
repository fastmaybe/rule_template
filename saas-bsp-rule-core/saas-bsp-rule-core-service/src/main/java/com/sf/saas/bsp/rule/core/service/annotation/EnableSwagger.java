package com.sf.saas.bsp.rule.core.service.annotation;

import com.sf.saas.bsp.rule.core.service.config.swagger.SwaggerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA

 * @author: 01403823
 * @date: 2022/1/12 17:43
 * @Email: yahya@sf-express.com
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SwaggerConfiguration.class)
public @interface EnableSwagger {
}