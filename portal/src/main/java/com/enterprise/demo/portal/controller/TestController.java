package com.enterprise.demo.portal.controller;

import com.enterprise.demo.permission.dao.PermissionEntity;
import com.enterprise.demo.permission.service.PermissionService;
import com.enterprise.demo.portal.dto.RequestDTO;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private PermissionService permissionService;

  @PostMapping("/permissions")
  public List<PermissionEntity> sayHello(@RequestBody RequestDTO requestDTO) {
    log.info("request:{}", requestDTO);
    return permissionService.selectAll();
  }

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
