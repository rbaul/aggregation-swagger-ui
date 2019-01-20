# Aggregation Swagger UI Service
> Spring Boot 2.0 application service that can aggregate a api-docs of all micro-service into a single Swagger-UI.

[![Build Status](https://travis-ci.com/rbaul/aggregation-swagger-ui.svg?branch=master)](https://travis-ci.com/rbaul/aggregation-swagger-ui)
[![CodeFactor](https://www.codefactor.io/repository/github/rbaul/aggregation-swagger-ui/badge)](https://www.codefactor.io/repository/github/rbaul/aggregation-swagger-ui)
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)


#### Configuration:
In the **application.yml** file, configure all micro-service api-docs endpoints. This is what Swagger-UI consumes.

```yaml
documentation:
  baseurl: http://localhost:8081
  swagger:
    api-docs:
      -
        name: Swagger Petstore 1
        url: https://petstore.swagger.io/v2/swagger.json
        version: 2.0
      -
        name: Sample Group 1
        url: ${documentation.baseurl}/v2/api-docs?group=Samples%20Group%201
        version: 2.0
      -
        name: Sample Group 2
        url: ${documentation.baseurl}/v2/api-docs?group=Samples%20Group%202
        version: 2.0
    resources:
      -
        name: Sample Service resource
        url: ${documentation.baseurl}/swagger-resources
```

Also can be configure without IP, that will be use service base url

```yaml
documentation:
  swagger:
    api-docs:
      -
        name: Sample Group 1
        url: /v2/api-docs?group=Samples%20Group%201
        version: 2.0
      -
        name: Sample Group 2
        url: /v2/api-docs?group=Samples%20Group%202
        version: 2.0
```

## Run Sample
> Run `AggregationSwaggerUiApplication.java` for aggregation swagger ui server [http://localhost:8080/swagger-ui.html]()

> Run `SwaggerApiDocSamplesApplication.java` for swagger sample documentations [http://localhost:8081/swagger-ui.html]()

## License

Licensed under the [Apache License, Version 2.0].  

[Apache License, Version 2.0]: LICENSE
