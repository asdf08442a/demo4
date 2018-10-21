package com.enterprise.demo.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * <p>
 * 角色权限表
 * </p>
 */
@TableName("role_permission")
@Data
public class RolePermission implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  /**
   * 角色id
   */
  @TableField("role_id")
  private String roleId;
  /**
   * 权限id
   */
  @TableField("permission_id")
  private String permissionId;
  /**
   * 创建时间
   */
  @TableField("gmt_create")
  private Date gmtCreate;
  /**
   * 修改时间
   */
  @TableField("gmt_modified")
  private Date gmtModified;
}
