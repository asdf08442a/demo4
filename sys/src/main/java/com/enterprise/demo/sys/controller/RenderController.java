package com.enterprise.demo.sys.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RenderController {

  /**
   * 首页
   */
  @RequestMapping(value = {"/", "/index"})
  public String index() {
    return "index/index";
  }

  /**
   * 工作台
   */
  @GetMapping("/workdest")
  public String workdest() {
    return "index/workdest";
  }

  /**
   * 用户列表入口
   */
  @RequiresPermissions("users")
  @GetMapping("/users")
  public String userList() {
    return "user/list";
  }

  /**
   * 角色列表入口
   */
  @RequiresPermissions("roles")
  @GetMapping("/roles")
  public String roleList() {
    return "role/list";
  }

  /**
   * 权限列表入口
   */
  @RequiresPermissions("permissions")
  @GetMapping("/permissions")
  public String permissionList() {
    return "permission/list";
  }

  /**
   * 在线用户入口
   */
  @GetMapping("/online/users")
  public String onlineUsers() {
    return "onlineUsers/list";
  }

  /**
   * 数据监控入口
   */
  @GetMapping(value = "/database/monitoring")
  public String databaseMonitoring() {
    return "database/monitoring";
  }

  /**
   * 图标
   */
  @GetMapping(value = "/icons")
  public String getIcons() {
    return "ui/icons";
  }
}
