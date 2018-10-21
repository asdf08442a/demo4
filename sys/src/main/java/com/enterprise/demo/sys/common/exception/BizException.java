package com.enterprise.demo.sys.common.exception;

/**
 * 自定义业务异常
 **/
public class BizException extends RuntimeException {

  /**
   * 自定义错误信息
   */
  public BizException(String message) {
    super(message);
  }

}
