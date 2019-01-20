package com.github.rbaul.spring.boot.swaggerui.aggregation.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@EnableAutoConfiguration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "documentation.swagger")
public class SwaggerServicesConfig {

    private List<SwaggerResource> resources;
    private List<SwaggerApiDoc> apiDocs;

    @Getter
    @Setter
    @ToString
    @EnableConfigurationProperties
    public static class SwaggerResource {
        private String name;
        private String url;
    }

    @Getter
    @Setter
    @ToString
    @EnableConfigurationProperties
    @ConfigurationProperties(prefix = "documentation.swagger.api-docs")
    public static class SwaggerApiDoc {
        private String name;
        private String url;
        private String version;
    }

}
