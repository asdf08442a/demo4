package com.enterprise.demo.portal.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

  @GetMapping("/get")
  public String get() {
    log.info("workdest request");
    return "get";
  }

  @PostMapping("/post")
  public String post(String request) {
    log.info("request:{}", request);
    return "post";
  }
}
