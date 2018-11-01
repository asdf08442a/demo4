package com.enterprise.demo.permission.service;

import com.enterprise.demo.permission.dao.PermissionEntity;
import com.enterprise.demo.permission.dao.PermissionExample;
import com.enterprise.demo.permission.dao.PermissionMapper;
import com.enterprise.demo.permission.dao.RolePermissionEntity;
import com.enterprise.demo.permission.dao.RolePermissionExample;
import com.enterprise.demo.permission.dao.RolePermissionMapper;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jinzg
 * @date 2018/10/28
 */
@Service
public class PermissionService {

  @Autowired
  private RoleService roleService;
  @Autowired
  private RolePermissionMapper rolePermissionMapper;
  @Autowired
  private PermissionMapper permissionMapper;

  public List<PermissionEntity> selectAll() {
    PermissionExample example = new PermissionExample();
    return permissionMapper.selectByExample(example);
  }

  public Set<String> selectPermsByRoleIds(Set<String> roleIds) {
    List<PermissionEntity> permissionEntities = selectPermEntitiesByRoleIds(roleIds);
    if (CollectionUtils.isEmpty(permissionEntities)) {
      return null;
    }
    Set<String> perms = Sets.newHashSet();
    for (PermissionEntity entity : permissionEntities) {
      if (StringUtils.isBlank(entity.getPerms())) {
        continue;
      }
      perms.add(entity.getPerms());
    }
    return perms;
  }

  public List<PermissionEntity> selectPermEntitiesByRoleIds(Set<String> roleIds) {
    // 查询角色权限
    RolePermissionExample rolePermissionExample = new RolePermissionExample();
    rolePermissionExample.createCriteria().andRoleIdIn(new ArrayList<>(roleIds));
    List<RolePermissionEntity> rolePermissionEntities = rolePermissionMapper
        .selectByExample(rolePermissionExample);
    if (CollectionUtils.isEmpty(rolePermissionEntities)) {
      return null;
    }
    Set<String> permIds = Sets.newHashSet();
    rolePermissionEntities
        .forEach(rolePermissionEntity -> permIds.add(rolePermissionEntity.getPermissionId()));

    // 查询权限
    PermissionExample permissionExample = new PermissionExample();
    permissionExample.createCriteria().andPermissionIdIn(new ArrayList<>(permIds));
    List<PermissionEntity> permissionEntities = permissionMapper.selectByExample(permissionExample);
    if (CollectionUtils.isEmpty(permissionEntities)) {
      return null;
    }
    return permissionEntities;
  }

  public List<PermissionEntity> selectPermEntitiesByUserId(String userId) {
    Set<String> roleIds = roleService.selectRoleIdsByUserId(userId);
    return selectPermEntitiesByRoleIds(roleIds);
  }
}
