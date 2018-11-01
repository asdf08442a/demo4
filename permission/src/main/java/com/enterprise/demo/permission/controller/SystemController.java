package com.enterprise.demo.permission.controller;

import com.enterprise.demo.common.CommonConstant;
import com.enterprise.demo.common.dto.ResponseDTO;
import com.enterprise.demo.common.util.ResultUtils;
import com.enterprise.demo.permission.BizException;
import com.enterprise.demo.permission.dao.UserEntity;
import com.enterprise.demo.permission.dto.LoginResponse;
import com.enterprise.demo.permission.service.PermissionService;
import com.enterprise.demo.permission.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinzg
 * @date 2018/10/28
 */
@RestController
@Slf4j
public class SystemController {

  @Autowired
  private PermissionService permissionService;
  @Autowired
  private UserService userService;

  /**
   * 提交登录
   */
  @PostMapping("/login")
  public ResponseDTO login(@RequestParam("userId") String userId,
      @RequestParam("token") String token) {
    // 入参校验
    if (StringUtils.isBlank(userId) || StringUtils.isBlank(token)) {
      throw new BizException(CommonConstant.PARAM_NULL);
    }
    // 登陆
    UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userId,
        token.toCharArray());
    try {
      Subject subject = SecurityUtils.getSubject();
      subject.login(usernamePasswordToken);
    } catch (Exception e) {
      log.error("登录异常", e);
      usernamePasswordToken.clear();
      throw e;
    }

    // 更新登录时间
    userService.updateLastLoginTime((UserEntity) SecurityUtils.getSubject().getPrincipal());

    // sessionId
    String sid = (String) SecurityUtils.getSubject().getSession().getId();
    log.info("session id = {}", sid);

    return ResultUtils
        .success(new LoginResponse(sid, permissionService.selectPermEntitiesByUserId(userId)));
  }

  /**
   * 提交登出
   */
  @PostMapping("/logout")
  public ResponseDTO logout() {
    SecurityUtils.getSubject().logout();
    return ResultUtils.success();
  }

  /**
   * 用户没有登录
   */
  @RequestMapping("/login")
  public ResponseDTO login() {
    return ResultUtils.success("用户没有登录");
  }

  /**
   * 没有权限
   */
  @RequestMapping("/noPermission")
  public ResponseDTO noPermission() {
    return ResultUtils.success("用户无权限访问");
  }

}
