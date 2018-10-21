package com.enterprise.demo.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * <p>
 * 权限表
 * </p>
 */
@Data
public class Permission implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  /**
   * 权限id
   */
  @TableField("permission_id")
  private String permissionId;
  /**
   * 权限名称
   */
  private String name;
  /**
   * 权限描述
   */
  private String description;
  /**
   * 权限访问路径
   */
  private String url;
  /**
   * 权限标识
   */
  private String perms;
  /**
   * 父级权限id
   */
  @TableField("parent_id")
  private String parentId;
  /**
   * 类型 0：目录 1：菜单 2：按钮
   */
  private Integer type;
  /**
   * 排序
   */
  @TableField("order_num")
  private Integer orderNum;
  /**
   * 图标
   */
  private String icon;
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
