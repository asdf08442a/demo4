package com.enterprise.demo.permission.service;

import com.enterprise.demo.permission.dao.PermissionEntity;
import com.enterprise.demo.permission.dao.PermissionExample;
import com.enterprise.demo.permission.dao.PermissionMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jinzg
 * @date 2018/10/28
 */
@Service
public class PermissionService {

  @Autowired
  private PermissionMapper permissionMapper;

  public List<PermissionEntity> selectAll() {
    PermissionExample example = new PermissionExample();
    return permissionMapper.selectByExample(example);
  }
}
