package com.enterprise.demo.permission;

import com.enterprise.demo.common.CommonConstant;

/**
 * 自定义业务异常
 **/
public class BizException extends RuntimeException {

  private int code;

  public BizException(String message) {
    super(message);
    this.code = CommonConstant.FAIL_CODE;
  }

  public BizException(int code, String message) {
    super(message);
    this.code = code;
  }

}
