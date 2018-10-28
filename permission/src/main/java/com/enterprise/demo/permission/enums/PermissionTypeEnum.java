package com.enterprise.demo.permission.enums;

import com.xiaoju.manhattan.auto.framework.mybatis.ICodeEnum;

/**
 * @author jinzg
 * @date 2018/10/28
 */
public enum PermissionTypeEnum implements ICodeEnum {
  /**
   * 目录
   */
  CATALOG(0, "目录"),
  /**
   * 菜单
   */
  MENU(1, "菜单"),
  /**
   * 按钮
   */
  BUTTON(2, "按钮");

  private byte code;
  private String desc;

  PermissionTypeEnum(int code, String desc) {
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
