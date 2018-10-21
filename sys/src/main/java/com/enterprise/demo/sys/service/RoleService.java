package com.enterprise.demo.sys.service;

import com.enterprise.demo.sys.dto.base.ResponseDTO;
import com.enterprise.demo.sys.entity.Permission;
import com.enterprise.demo.sys.entity.Role;
import java.util.List;
import java.util.Set;

public interface RoleService {

  Set<String> findRoleByUserId(String userId);

  List<Role> selectRoles(String name);

  int insert(Role role);

  int deleteRole(List<String> roleIds);

  Role findByRoleId(String roleId);

  int updateByRoleId(Role role);

  List<Permission> findPermissionsByRoleId(String roleId);

  ResponseDTO addAssignPermission(String roleId, List<String> permissionIdsList);

  Set<String> findUserIdsByRoleId(String roleId);
}
