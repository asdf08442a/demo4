package com.enterprise.demo.permission.dto;

import com.enterprise.demo.permission.dao.PermissionEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author jinzg
 * @date 2018/10/30
 */
@Data
@AllArgsConstructor
public class LoginResponse {

  private String sessionId;
  private List<PermissionEntity> permissionEntityList;

}
