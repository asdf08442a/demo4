package com.enterprise.demo.permission.enums;

import com.xiaoju.manhattan.auto.framework.mybatis.ICodeEnum;

/**
 * @author jinzg
 * @date 2018/10/28
 */
public enum StatusEnum implements ICodeEnum {
  /**
   * 无效
   */
  UNUSE(0, "目录"),
  /**
   * 有效
   */
  USE(1, "菜单");

  private byte code;
  private String desc;

  StatusEnum(int code, String desc) {
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
