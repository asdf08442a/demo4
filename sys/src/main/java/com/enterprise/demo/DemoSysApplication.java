package com.enterprise.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoSysApplication {

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(DemoSysApplication.class);
    // 屏蔽命令行访问属性
    springApplication.setAddCommandLineProperties(false);
    springApplication.run(args);
  }
}
