package com.enterprise.demo.permission.service;

import com.enterprise.demo.permission.dao.UserRoleEntity;
import com.enterprise.demo.permission.dao.UserRoleExample;
import com.enterprise.demo.permission.dao.UserRoleMapper;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jinzg
 * @date 2018/10/30
 */
@Service
@Slf4j
public class RoleService {

  @Autowired
  private UserRoleMapper userRoleMapper;

  /**
   * 根据用户id查询角色
   */
  public Set<String> selectRoleIdsByUserId(String userId) {
    UserRoleExample example = new UserRoleExample();
    example.createCriteria().andUserIdEqualTo(userId);
    List<UserRoleEntity> userRoleEntityList = userRoleMapper.selectByExample(example);
    if (CollectionUtils.isEmpty(userRoleEntityList)) {
      return null;
    }
    Set<String> roleIds = Sets.newHashSet();
    userRoleEntityList.forEach(userRoleEntity -> roleIds.add(userRoleEntity.getRoleId()));
    return roleIds;
  }


}
