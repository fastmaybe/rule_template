package com.sf.saas.bsp.rule.core.service.config.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by IntelliJ IDEA
 *
 * @author: 01403823
 * @date: 2022/1/12 17:14
 * @Email: yahya@sf-express.com
 */
@EnableKnife4j
@ConditionalOnWebApplication
@EnableSwagger2
public class SwaggerConfiguration {

    // Boolean 优于 boolean (必须要存在该配置项，否则启动报错)
    @Value("${swagger.enabled}")
    private Boolean enable;

    @Bean
    public Docket createRestApi() {
        BaseSwaggerConfig baseSwagger = new BaseSwaggerConfig();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(baseSwagger.pars)
                .securitySchemes(baseSwagger.securitySchemes())
                .securityContexts(baseSwagger.securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DDS-BSP-RULE下单规则")
                .description("DDS-BSP-RULE下单规则")
                .version("v1.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("孙涛", "", "suntao32@sf-express.com"))
                .build();
    }

}
