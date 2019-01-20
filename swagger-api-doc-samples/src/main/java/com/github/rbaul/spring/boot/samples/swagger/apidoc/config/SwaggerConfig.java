package com.github.rbaul.spring.boot.samples.swagger.apidoc.config;

import com.github.rbaul.spring.boot.samples.swagger.apidoc.web.controllers.group1.Group1SampleApiController;
import com.github.rbaul.spring.boot.samples.swagger.apidoc.web.controllers.group2.Group2SampleApiController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

import static org.apache.tomcat.websocket.Constants.AUTHORIZATION_HEADER_NAME;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String AUTHORIZATION_TOKEN_REFERENCE = "Authorization-Token";
    private static final String AUTHORIZATION_HEADER_NAME = "token";

    @Value("${application.formatted-version:development}")
    private String version;

    @Value("${application.title:Samples}")
    private String title;

    @Value("${application.ip:localhost:8081}")
    private String baseIp;

    @Bean
    public Docket apiGroup1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Samples Group 1")
                .host(baseIp)
                .select()
                .apis(RequestHandlerSelectors.basePackage(Group1SampleApiController.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    @Bean
    public Docket apiGroup2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Samples Group 2")
                .enable(true)
                .enableUrlTemplating(true)
                .host(baseIp)
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()))
                .select()
                .apis(RequestHandlerSelectors.basePackage(Group2SampleApiController.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .version(version)
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey(AUTHORIZATION_TOKEN_REFERENCE, AUTHORIZATION_HEADER_NAME, ApiKeyVehicle.HEADER.getValue());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authScopes = new AuthorizationScope[1];
        authScopes[0] = new AuthorizationScopeBuilder()
                .scope("global")
                .description("full access").build();
        return Collections.singletonList(SecurityReference.builder()
                .reference(AUTHORIZATION_TOKEN_REFERENCE)
                .scopes(authScopes).build());
    }
}