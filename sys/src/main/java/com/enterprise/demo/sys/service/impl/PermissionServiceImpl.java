package com.enterprise.demo.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.enterprise.demo.sys.common.util.IdWorker;
import com.enterprise.demo.sys.dao.PermissionMapper;
import com.enterprise.demo.sys.dao.RolePermissionMapper;
import com.enterprise.demo.sys.dao.UserRoleMapper;
import com.enterprise.demo.sys.entity.Permission;
import com.enterprise.demo.sys.entity.RolePermission;
import com.enterprise.demo.sys.service.PermissionService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class PermissionServiceImpl implements PermissionService {

  @Autowired
  private PermissionMapper permissionMapper;
  @Autowired
  private UserRoleMapper userRoleMapper;
  @Autowired
  private RolePermissionMapper rolePermissionMapper;

  @Override
  public List<Permission> selectAll() {
    return permissionMapper.selectList(new EntityWrapper<Permission>().orderBy("order_num"));
  }

  @Override
  public Set<String> selectPermsByUserId(String userId) {
    Set<String> roleIds = userRoleMapper.selectRoleIdsByUserId(userId);
    Set<String> permissionIds = rolePermissionMapper.selectPermissionIdsByRoleIds(roleIds);
    return permissionMapper.selectPermsByPermissionIds(permissionIds);
  }

  @Override
  public List<Permission> selectMenuByUserId(String userId) {
    Set<String> roleIds = userRoleMapper.selectRoleIdsByUserId(userId);
    Set<String> permissionIds = rolePermissionMapper.selectPermissionIdsByRoleIds(roleIds);
    return permissionMapper.selectMenuByPermissionIds(permissionIds);
  }

  @Override
  public List<Permission> selectAllMenuName() {
    return permissionMapper.selectList(new EntityWrapper<Permission>()
        .in("type", new String[]{"0", "1"})
        .orderBy("order_num")
    );
  }

  @Override
  public int insert(Permission permission) {
    permission.setPermissionId(String.valueOf(IdWorker.genPermissionId()));
    return permissionMapper.insert(permission);
  }

  @Override
  public int selectSubPermsByPermissionId(String permissionId) {
    return permissionMapper
        .selectCount(new EntityWrapper<Permission>().eq("parent_id", permissionId));
  }

  @Override
  public int deletePermission(String permissionId) {
    return permissionMapper
        .delete(new EntityWrapper<Permission>().eq("permission_id", permissionId));
  }

  @Override
  public Permission selectByPermissionId(String permissionId) {
    List<Permission> permissions = permissionMapper.selectList(new EntityWrapper<Permission>()
        .eq("permission_id", permissionId));
    if (CollectionUtils.isEmpty(permissions)) {
      return null;
    }
    return permissions.get(0);
  }

  @Override
  public Permission selectByParentId(String parentId) {
    List<Permission> permissions = permissionMapper.selectList(new EntityWrapper<Permission>()
        .eq("parent_id", parentId));
    if (CollectionUtils.isEmpty(permissions)) {
      return null;
    }
    return permissions.get(0);
  }

  @Override
  public int updateByPermissionId(Permission permission) {
    return permissionMapper.update(permission, new EntityWrapper<Permission>()
        .eq("permission_id", permission.getPermissionId()));
  }

  @Override
  public int selectRolePermissionCnt(String permissionId) {
    return rolePermissionMapper.selectCount(new EntityWrapper<RolePermission>()
        .eq("permission_id", permissionId));
  }
}
