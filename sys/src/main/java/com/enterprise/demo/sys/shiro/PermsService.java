package com.enterprise.demo.sys.shiro;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

/**
 * js调用thymeleaf 实现按钮权限
 */
@Component("perms")
public class PermsService {

  public boolean hasPerm(String permission) {
    return SecurityUtils.getSubject().isPermitted(permission);
  }
}
