package com.enterprise.demo.permission.controller;

import com.enterprise.demo.common.dto.ResponseDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinzg
 * @date 2018/10/28
 */
@RestController
public class SystemController {

  /**
   * 提交登录
   */
  @PostMapping("/login")
  public ResponseDTO login(@RequestParam("userId") String userId,
      @RequestParam("token") String token) {
    // 入参校验

    // 登陆
    UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userId, token);
    try {
      Subject subject = SecurityUtils.getSubject();
      subject.login(usernamePasswordToken);
    } catch (Exception e) {
      usernamePasswordToken.clear();
      return new ResponseDTO();
    }

    // 查询权限
    String s = (String) SecurityUtils.getSubject().getSession().getId();
    System.out.println("sessionId = " + s);
    return new ResponseDTO();
  }

}
