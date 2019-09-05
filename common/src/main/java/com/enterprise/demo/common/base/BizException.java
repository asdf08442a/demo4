package com.enterprise.demo.common.base;

/**
 * 业务异常
 */
public class BizException extends RuntimeException {

  public BizException(String msg) {
    super(msg);
  }
}
