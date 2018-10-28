package com.enterprise.demo.permission.enums;

import com.xiaoju.manhattan.auto.framework.mybatis.ICodeEnum;

/**
 * @author jinzg
 * @date 2018/10/28
 */
public enum RoleTypeEnum implements ICodeEnum {
  /**
   * 商户
   */
  TENANT(0, "商户"),
  /**
   * 用户
   */
  USER(1, "用户");

  private byte code;
  private String desc;

  RoleTypeEnum(int code, String desc) {
    this.code = (byte) code;
    this.desc = desc;
  }

  @Override
  public byte code() {
    return this.code;
  }

  @Override
  public String desc() {
    return this.desc;
  }
}
