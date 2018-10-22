package com.enterprise.demo.sys.controller;

import com.enterprise.demo.sys.common.exception.BizException;
import com.enterprise.demo.sys.common.util.ResultUtils;
import com.enterprise.demo.sys.dto.base.ResponseDTO;
import com.enterprise.demo.sys.entity.Permission;
import com.enterprise.demo.sys.entity.User;
import com.enterprise.demo.sys.service.PermissionService;
import com.enterprise.demo.sys.service.UserService;
import com.enterprise.demo.sys.shiro.filter.KickoutSessionControlFilter;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Deque;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.subject.Subject;
import org.crazycake.shiro.RedisCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class SystemController {

  @Autowired
  private DefaultKaptcha defaultKaptcha;
  @Autowired
  private UserService userService;
  @Autowired
  private PermissionService permissionService;
  @Autowired
  private RedisCacheManager redisCacheManager;

  @GetMapping("/login")
  public String login() {
    return "system/login";
  }

  /**
   * 获取验证码图片
   */
  @RequestMapping("/verificationCode")
  public void getCaptchaCode(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    HttpSession session = request.getSession();
    response.setDateHeader("Expires", 0);
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
    response.addHeader("Cache-Control", "post-check=0, pre-check=0");
    response.setHeader("Pragma", "no-cache");
    response.setContentType("image/jpeg");
    //生成验证码文本
    String capText = defaultKaptcha.createText();
    session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
    log.info("生成验证码文本:{}", capText);
    //利用生成的字符串构建图片
    BufferedImage bi = defaultKaptcha.createImage(capText);
    ServletOutputStream out = response.getOutputStream();
    ImageIO.write(bi, "jpg", out);
    try {
      out.flush();
    } finally {
      out.close();
    }
  }

  /**
   * 提交登录
   */
  @PostMapping("/login")
  @ResponseBody
  public ResponseDTO login(HttpServletRequest request, String username, String password,
      String verification, Integer rememberMe) {
    // 判断验证码
    String rightCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
    if (StringUtils.isBlank(verification) || StringUtils.isBlank(rightCode) || !verification
        .equals(rightCode)) {
      return ResultUtils.error("验证码错误！");
    }
    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    try {
      token.setRememberMe(rememberMe != null && 1 == rememberMe);
      Subject subject = SecurityUtils.getSubject();
      subject.login(token);
    } catch (LockedAccountException e) {
      token.clear();
      return ResultUtils.error("用户已经被锁定不能登录，请联系管理员！");
    } catch (AuthenticationException e) {
      token.clear();
      return ResultUtils.error("用户名或者密码错误！");
    }
    //更新最后登录时间
    userService.updateLastLoginTime((User) SecurityUtils.getSubject().getPrincipal());
    return ResultUtils.success("登录成功！");
  }

  /**
   * 获取当前登录用户的菜单
   */
  @PostMapping("/menu")
  @ResponseBody
  public List<Permission> getMenus() {
    List<Permission> permissionListList = permissionService.selectMenuByUserId(
        ((User) SecurityUtils.getSubject().getPrincipal()).getUserId());
    return permissionListList;
  }

  /**
   * 注册
   */
  @GetMapping(value = "/register")
  public String register() {
    return "system/register";
  }

  /**
   * 提交注册
   */
  @PostMapping("/register")
  @ResponseBody
  public ResponseDTO register(HttpServletRequest request, User registerUser, String confirmPassword,
      String
          verification) {
    // 判断验证码
    String rightCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
    if (StringUtils.isBlank(verification) || StringUtils.isBlank(rightCode) || !verification.equals
        (rightCode)) {
      return ResultUtils.error("验证码错误！");
    }
    String username = registerUser.getUsername();
    User user = userService.selectByUsername(username);
    if (user != null) {
      return ResultUtils.error("用户名已存在！");
    }
    String password = registerUser.getPassword();
    // 判断两次输入密码是否相等
    if (StringUtils.isBlank(confirmPassword) || StringUtils.isBlank(password) || !confirmPassword
        .equals
            (password)) {
      return ResultUtils.error("两次密码不一致！");
    }
    // 注册
    int registerResult = userService.insert(registerUser);
    if (registerResult > 0) {
      return ResultUtils.success("注册成功！");
    } else {
      return ResultUtils.error("注册失败，请稍后再试！");
    }
  }

  /**
   * 踢出
   */
  @GetMapping("/kickout")
  public String kickout() {
    return "system/kickout";
  }

  /**
   * 登出
   */
  @RequestMapping(value = "/logout")
  @ResponseBody
  public ResponseDTO logout() {
    Subject subject = SecurityUtils.getSubject();
    if (subject == null) {
      throw new BizException("用户已登出");
    }
    String username = ((User) SecurityUtils.getSubject().getPrincipal()).getUsername();
    Serializable sessionId = SecurityUtils.getSubject().getSession().getId();
    Cache<String, Deque<Serializable>> cache = redisCacheManager.getCache(
        KickoutSessionControlFilter.SHIRO_REDIS_KEY);
    Deque<Serializable> deques = cache.get(username);
    if (CollectionUtils.isNotEmpty(deques)) {
      for (Serializable deque : deques) {
        if (sessionId.equals(deque)) {
          deques.remove(deque);
          break;
        }
      }
      cache.put(username, deques);
    }
    subject.logout();
    return ResultUtils.success("退出成功");
  }
}
