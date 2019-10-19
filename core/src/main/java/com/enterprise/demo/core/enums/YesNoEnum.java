package com.enterprise.demo.core.enums;

public enum YesNoEnum {

  YES(1, "是"),

  NO(0, "否");

  YesNoEnum(int key, String value) {
    this.key = key;
    this.value = value;
  }

  private int key;
  private String value;

  public int getKey() {
    return key;
  }

  public void setKey(int key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
