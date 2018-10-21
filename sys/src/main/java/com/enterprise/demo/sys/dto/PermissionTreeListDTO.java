package com.enterprise.demo.sys.dto;

import lombok.Data;

@Data
public class PermissionTreeListDTO {

  private Integer id;
  private String permissionId;
  private String name;
  private String parentId;
  private Boolean open = true;
  private Boolean checked = false;
}
