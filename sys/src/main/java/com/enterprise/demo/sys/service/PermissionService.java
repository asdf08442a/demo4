package com.enterprise.demo.sys.service;

import com.enterprise.demo.sys.entity.Permission;
import java.util.List;
import java.util.Set;

public interface PermissionService {

  List<Permission> selectAll();

  Set<String> selectPermsByUserId(String userId);

  List<Permission> selectMenuByUserId(String userId);

  List<Permission> selectAllMenuName();

  int insert(Permission permission);

  int selectSubPermsByPermissionId(String permissionId);

  int deletePermission(String permissionId);

  Permission selectByPermissionId(String permissionId);

  Permission selectByParentId(String parentId);

  int updateByPermissionId(Permission permission);

  int selectRolePermissionCnt(String permissionId);
}
