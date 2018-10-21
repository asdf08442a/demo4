package com.enterprise.demo.sys.controller;

import com.enterprise.demo.sys.common.CoreConst;
import com.enterprise.demo.sys.common.util.ResultUtils;
import com.enterprise.demo.sys.dto.base.ResponseDTO;
import com.enterprise.demo.sys.entity.Permission;
import com.enterprise.demo.sys.service.PermissionService;
import com.enterprise.demo.sys.shiro.ShiroService;
import com.google.common.collect.Lists;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
@Slf4j
public class PermissionController {

  /**
   * 1:全部资源，2：菜单资源
   */
  private static final String[] MENU_FLAG = {"1", "2"};
  @Autowired
  private PermissionService permissionService;
  @Autowired
  private ShiroService shiroService;

  /**
   * 添加权限
   */
  @PostMapping("/add")
  @ResponseBody
  public ResponseDTO addPermission(Permission permission) {
    try {
      int a = permissionService.insert(permission);
      if (a > 0) {
        shiroService.updatePermission();
        return ResultUtils.success("添加权限成功");
      } else {
        return ResultUtils.error("添加权限失败");
      }
    } catch (Exception e) {
      log.error("PermissionController.addPermission:{}", e);
      throw e;
    }
  }

  /**
   * 删除权限
   */
  @PostMapping("/delete")
  @ResponseBody
  public ResponseDTO deletePermission(String permissionId) {
    try {
      int subPermsByPermissionIdCount = permissionService
          .selectSubPermsByPermissionId(permissionId);
      if (subPermsByPermissionIdCount > 0) {
        return ResultUtils.error("该资源存在下级资源，无法删除！");
      }
      int rolePermissionCnt = permissionService.selectRolePermissionCnt(permissionId);
      if (rolePermissionCnt > 0) {
        return ResultUtils.error("该资源下还有角色，无法删除！");
      }
      int a = permissionService.deletePermission(permissionId);
      if (a > 0) {
        shiroService.updatePermission();
        return ResultUtils.success("删除权限成功");
      } else {
        return ResultUtils.error("删除权限失败");
      }
    } catch (Exception e) {
      log.error("PermissionController.deletePermission:{}", e);
      throw e;
    }
  }

  /**
   * 权限列表数据
   */
  @PostMapping("/list")
  @ResponseBody
  public List<Permission> loadPermissions(String flag) {
    List<Permission> permissionListList = Lists.newArrayList();
    if (StringUtils.isBlank(flag) || MENU_FLAG[0].equals(flag)) {
      permissionListList = permissionService.selectAll();
    } else if (MENU_FLAG[1].equals(flag)) {
      permissionListList = permissionService.selectAllMenuName();
    }
    return permissionListList;
  }

  /**
   * 权限详情
   */
  @GetMapping("/edit")
  public String detail(Model model, String permissionId) {
    Permission permission = permissionService.selectByPermissionId(permissionId);
    if (null != permission) {
      if (permission.getParentId().equals(CoreConst.TOP_MENU_ID)) {
        model.addAttribute("parentName", CoreConst.TOP_MENU_NAME);
      } else {
        Permission parent = permissionService.selectByParentId(permission.getParentId());
        model.addAttribute("parentName", parent.getName());
      }
    }
    model.addAttribute("permission", permission);
    return "permission/detail";
  }

  /**
   * 编辑权限
   */
  @PostMapping("/edit")
  @ResponseBody
  public ResponseDTO editPermission(@ModelAttribute("permission") Permission permission) {
    int a = permissionService.updateByPermissionId(permission);
    if (a > 0) {
      shiroService.updatePermission();
      return ResultUtils.success("编辑权限成功");
    } else {
      return ResultUtils.error("编辑权限失败");
    }
  }

}
