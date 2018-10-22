package com.enterprise.demo.sys.controller;

import com.enterprise.demo.sys.common.CoreConst;
import com.enterprise.demo.sys.common.util.PageUtils;
import com.enterprise.demo.sys.common.util.PasswordHelper;
import com.enterprise.demo.sys.common.util.ResultUtils;
import com.enterprise.demo.sys.dto.ChangePasswordDTO;
import com.enterprise.demo.sys.dto.base.PageResultDTO;
import com.enterprise.demo.sys.dto.base.ResponseDTO;
import com.enterprise.demo.sys.entity.Role;
import com.enterprise.demo.sys.entity.User;
import com.enterprise.demo.sys.service.RoleService;
import com.enterprise.demo.sys.service.UserService;
import com.enterprise.demo.sys.shiro.MyShiroRealm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api("userController相关的api")
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private RoleService roleService;
  @Autowired
  private MyShiroRealm myShiroRealm;

  /**
   * 用户列表数据
   */
  @ApiOperation(value = "查询用户列表", notes = "查询用户列表")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "username", value = "username", required = false, dataType =
          "String", paramType = "query"),
      @ApiImplicitParam(name = "email", value = "email", required = false, dataType = "String",
          paramType =
              "query"),
      @ApiImplicitParam(name = "phone", value = "phone", required = false, dataType = "String",
          paramType =
              "query")})
  @PostMapping("/list")
  @ResponseBody
  public PageResultDTO loadUsers(User user, Integer limit, Integer offset) {
    PageHelper.startPage(PageUtils.getPageNo(limit, offset), limit);
    List<User> userList = userService.findUsers(user);
    PageInfo<User> pages = new PageInfo<>(userList);
    return ResultUtils.table(userList, pages.getTotal());
  }

  /**
   * 新增用户
   */
  @PostMapping("/add")
  @ResponseBody
  public ResponseDTO add(User userForm, String confirmPassword) {
    String username = userForm.getUsername();
    User user = userService.selectByUsername(username);
    if (user != null) {
      return ResultUtils.error("用户名已存在");
    }
    String password = userForm.getPassword();
    //判断两次输入密码是否相等
    if (confirmPassword != null && password != null && !confirmPassword.equals(password)) {
      return ResultUtils.error("两次密码不一致");
    }
    int num = userService.insert(userForm);
    if (num > 0) {
      return ResultUtils.success("添加用户成功");
    } else {
      return ResultUtils.error("添加用户失败");
    }
  }

  /**
   * 编辑用户详情
   */
  @GetMapping("/edit")
  public String userDetail(Model model, String userId) {
    log.debug("用户id：{}", userId);
    User user = userService.selectByUserId(userId);
    model.addAttribute("user", user);
    return "user/userDetail";
  }

  /**
   * 编辑用户
   */
  @PostMapping("/edit")
  @ResponseBody
  public ResponseDTO editUser(User user) {
    log.debug("用户：{}", user);
    int a = userService.updateByUserId(user);
    if (a > 0) {
      return ResultUtils.success("编辑用户成功！");
    } else {
      return ResultUtils.error("编辑用户失败");
    }
  }

  /**
   * 删除用户
   */
  @GetMapping("/delete")
  @ResponseBody
  public ResponseDTO deleteUser(String userId) {
    List<String> userIdsList = Arrays.asList(userId);
    int a = userService.updateStatusBatch(userIdsList, CoreConst.STATUS_INVALID);
    if (a > 0) {
      return ResultUtils.success("删除用户成功");
    } else {
      return ResultUtils.error("删除用户失败");
    }
  }

  /**
   * 批量删除用户
   */
  @GetMapping("/batch/delete")
  @ResponseBody
  public ResponseDTO batchDeleteUser(String userIdStr) {
    String[] userIds = userIdStr.split(",");
    List<String> userIdsList = Arrays.asList(userIds);
    int a = userService.updateStatusBatch(userIdsList, CoreConst.STATUS_INVALID);
    if (a > 0) {
      return ResultUtils.success("删除用户成功");
    } else {
      return ResultUtils.error("删除用户失败");
    }
  }

  /**
   * 分配角色列表查询
   */
  @PostMapping("/assign/role/list")
  @ResponseBody
  public Map<String, Object> assignRoleList(String userId) {
    List<Role> roleList = roleService.selectRoles(StringUtils.EMPTY);
    Set<String> hasRoles = roleService.findRoleByUserId(userId);
    Map<String, Object> jsonMap = Maps.newHashMap();
    jsonMap.put("rows", roleList);
    jsonMap.put("hasRoles", hasRoles);
    return jsonMap;
  }

  /**
   * 保存分配角色
   */
  @PostMapping("/assign/role")
  @ResponseBody
  public ResponseDTO assignRole(String userId, String roleIdStr) {
    String[] roleIds = roleIdStr.split(",");
    List<String> roleIdsList = Arrays.asList(roleIds);
    ResponseDTO ResponseDTO = userService.addAssignRole(userId, roleIdsList);
    Set<String> userIds = Sets.newHashSet();
    userIds.add(userId);
    myShiroRealm.clearAuthorizationByUserId(userIds);
    return ResponseDTO;
  }

  /**
   * 修改密码
   */
  @PostMapping(value = "/changePassword")
  @ResponseBody
  public ResponseDTO changePassword(ChangePasswordDTO changePasswordDTO) {
    if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmNewPassword())) {
      return ResultUtils.error("两次密码输入不一致");
    }
    User loginUser = userService.selectByUserId(((User) SecurityUtils.getSubject()
        .getPrincipal()).getUserId());
    User newUser = new User();
    BeanUtils.copyProperties(loginUser, newUser);
    String sysOldPassword = loginUser.getPassword();
    newUser.setPassword(changePasswordDTO.getOldPassword());
    String entryOldPassword = PasswordHelper.getPassword(newUser);
    if (sysOldPassword.equals(entryOldPassword)) {
      newUser.setPassword(changePasswordDTO.getNewPassword());
      PasswordHelper.encryptPassword(newUser);
      userService.updateByUserId(newUser);
      // 清除登录缓存
      Set<String> userIds = Sets.newHashSet();
      userIds.add(loginUser.getUserId());
      myShiroRealm.removeCachedAuthenticationInfo(userIds);
      /*SecurityUtils.getSubject().logout();*/
    } else {
      return ResultUtils.error("您输入的旧密码有误");
    }
    return ResultUtils.success("修改密码成功");
  }
}
