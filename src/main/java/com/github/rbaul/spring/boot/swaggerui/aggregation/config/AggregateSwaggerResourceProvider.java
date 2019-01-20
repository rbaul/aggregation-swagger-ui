package com.github.rbaul.spring.boot.swaggerui.aggregation.config;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor
@Component
@Primary
@EnableAutoConfiguration
public class AggregateSwaggerResourceProvider implements SwaggerResourcesProvider {
    @Autowired
    private SwaggerServicesConfig swaggerServicesConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${documentation.baseurl:http://localhost:8081}")
    private String baseUrl;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> swaggerResourceList = new ArrayList<>();

        // Resource
        for (SwaggerServicesConfig.SwaggerResource resource : swaggerServicesConfig.getResources()) {
            ResponseEntity<List<SwaggerResource>> exchange = restTemplate.exchange(resource.getUrl(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<SwaggerResource>>() {
                    });
            if(exchange.getStatusCode().is2xxSuccessful()){
                List<SwaggerResource> swaggerResources = exchange.getBody();
                if (!CollectionUtils.isEmpty(swaggerResources)) {
                    swaggerResources.forEach(swaggerResource ->
                        swaggerResource.setUrl(getEncodeUrl(swaggerResource)));
                    swaggerResourceList.addAll(swaggerResources.stream().peek(swaggerResource ->
                        swaggerResource.setName(generateResourceName(resource, swaggerResource)))
                            .collect(Collectors.toList()));
                }
            }
        }

        // Api Docs
        swaggerResourceList.addAll(swaggerServicesConfig.getApiDocs().stream()
                .map(service -> swaggerResource(service.getName(), service.getUrl(), service.getVersion()))
                .collect(Collectors.toList()));

        return swaggerResourceList;
    }

    private String generateResourceName(SwaggerServicesConfig.SwaggerResource resource, SwaggerResource swaggerResource) {
        return resource.getName() + ":" + swaggerResource.getName();
    }

    private String getEncodeUrl(SwaggerResource swaggerResource) {
        String parametersEncode = UriUtils.encodePath(swaggerResource.getUrl().split("\\?")[1], "UTF-8");
        return baseUrl + "/v2/api-docs?" + parametersEncode;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
