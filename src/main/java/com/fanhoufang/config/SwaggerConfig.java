package com.fanhoufang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
* Swagger配置类
*/
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket docket(){
       return new Docket(DocumentationType.OAS_30)
               // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
               // 是否启用Swagger
                .enable(true)

               .securitySchemes(Collections.singletonList(HttpAuthenticationScheme.JWT_BEARER_BUILDER
//                        显示用
                       .name("JWT")
                       .build()))
               .securityContexts(Collections.singletonList(SecurityContext.builder()
                       .securityReferences(Collections.singletonList(SecurityReference.builder()
                               .scopes(new AuthorizationScope[0])
                               .reference("JWT")
                               .build()))
                       // 声明作用域
                       .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
                       .build()))
               // 设置哪些接口暴露给Swagger展示
                .select()
                //apis： 添加swagger接口提取范围

               // 扫描指定包中的swagger注解
                .apis(RequestHandlerSelectors.basePackage("com.fanhoufang"))
               // 扫描所有有注解的api，用这种方式更灵活
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
               // 扫描所有
                //.apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();


    }
    
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("博客demo项目接口文档")
                .description("描述：用于管理接口")
                .contact(new Contact("fanhoufang", null, "fanhoufang@gmail.com"))
                .version("0.1")
                .build();
    }
}