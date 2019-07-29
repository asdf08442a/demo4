package com.enterprise.demo.portal.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinzg
 * @date 2018/10/18
 */
@RestController
@RequestMapping
@Slf4j
public class TestController {

  @PostMapping("/workdest")
  public String test() {
    log.info("workdest request");
    return "success";
  }

  @PostMapping("/roles")
  public String roles() {
    log.info("roles request");
    return "success";
  }

}
