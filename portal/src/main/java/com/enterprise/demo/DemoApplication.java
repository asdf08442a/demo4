package com.enterprise.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.enterprise.demo.core.dao")
@EnableCaching
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(DemoApplication.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }

}
