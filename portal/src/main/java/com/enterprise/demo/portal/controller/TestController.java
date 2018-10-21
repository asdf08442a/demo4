package com.enterprise.demo.portal.controller;

import com.enterprise.demo.portal.dto.RequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping("/test")
  public String sayHello(@RequestBody RequestDTO requestDTO) {
    log.info("request:{}", requestDTO);
    return "121212";
  }

}
