package com.sf.saas.bsp.rule.core.service.config.swagger;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class BaseSwaggerConfig {
    public List<Parameter> pars = new ArrayList<>();

    public ApiInfo apiInfo(String title, String description, String version, String name, String url, String email) {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .contact(new Contact("孙涛", "", "suntao32@sf-express.com"))
                .version(version)
                .build();
    }


    public List<ApiKey> securitySchemes() {
        return newArrayList(
                new ApiKey("Authorization", "api-token", "header"));
    }

    public List<SecurityContext> securityContexts() {
        return newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build()
        );
    }

    public List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
                new SecurityReference("Authorization", authorizationScopes));
    }

}
