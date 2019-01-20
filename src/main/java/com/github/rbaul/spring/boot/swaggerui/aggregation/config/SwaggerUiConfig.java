package com.github.rbaul.spring.boot.swaggerui.aggregation.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerUiConfig {

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .filter(true)
                .build();
    }

//    @Bean
//    public RestTemplate restTemplate()
//            throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
//
//        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
//                .loadTrustMaterial(null, acceptingTrustStrategy)
//                .build();
//
//        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
//
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setSSLSocketFactory(csf)
//                .build();
//
//        HttpComponentsClientHttpRequestFactory requestFactory =
//                new HttpComponentsClientHttpRequestFactory();
//
//        requestFactory.setHttpClient(httpClient);
//        RestTemplate restTemplate = new RestTemplate(requestFactory);
//        return restTemplate;
//    }

//    @Bean
//    public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder){
//        CloseableHttpClient httpClient
//                = HttpClients.custom()
//                .setSSLHostnameVerifier(new NoopHostnameVerifier())
//                .build();
//        HttpComponentsClientHttpRequestFactory requestFactory
//                = new HttpComponentsClientHttpRequestFactory(httpClient);
////        requestFactory.setHttpClient(httpClient);
////        new HttpComponentsClientHttpRequestFactory();
//
//        return new RestTemplate(requestFactory);
////        return restTemplateBuilder.requestFactory(requestFactory).build();
//    }

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.build();
    }
}