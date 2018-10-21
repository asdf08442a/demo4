package com.enterprise.demo.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * <p>
 * 用户表
 * </p>
 */
@Data
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  /**
   * 用户id
   */
  @TableField("user_id")
  private String userId;
  /**
   * 用户名
   */
  private String username;
  /**
   * 密码
   */
  private String password;
  /**
   * 加密盐值
   */
  private String salt;
  /**
   * 邮箱
   */
  private String email;
  /**
   * 联系方式
   */
  private String phone;
  /**
   * 性别：1男，2女
   */
  private Integer sex;
  /**
   * 年龄
   */
  private Integer age;
  /**
   * 用户状态：1有效; 2删除
   */
  private Integer status;
  /**
   * 最后登录时间
   */
  @TableField("last_login_time")
  private Date lastLoginTime;
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
