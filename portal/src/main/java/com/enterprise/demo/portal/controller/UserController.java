package com.enterprise.demo.portal.controller;

import com.enterprise.demo.common.base.BizResponse;
import com.enterprise.demo.core.dao.entity.UserEntity;
import com.enterprise.demo.core.service.base.UserService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  @Resource
  private UserService userService;

  @PostMapping("/getByUserId")
  public BizResponse<UserEntity> getByUserId(String userId) {
    log.info("request:{}", userId);
    return BizResponse.build(userService.getByUserId(userId));
  }
}
