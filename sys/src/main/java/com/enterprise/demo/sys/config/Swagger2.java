package com.enterprise.demo.sys.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置
 **/
@Profile({"dev", "test"})
@Configuration
@EnableSwagger2
public class Swagger2 {

  @Value("${spring.application.name}")
  private String title;

  @Bean
  public Docket createRestApi() {
    ApiInfo apiInfo = new ApiInfoBuilder()
        .title(title)
        .description("管理后台接口文档")
        .termsOfServiceUrl("https://github.com/asdf08442a/demo2-0")
        .contact(new Contact("jinzg", "", ""))
        .version("1.0")
        .build();
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.enterprise.demo.sys"))
        .paths(PathSelectors.any())
        .build();
  }
}
