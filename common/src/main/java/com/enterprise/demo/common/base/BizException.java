package com.enterprise.demo.common.base;

/**
 * 业务异常
 */
public class BizException extends RuntimeException {

  private final ErrorCode errorCode;

  public BizException() {
    super();
    this.errorCode = ErrorCode.BIZ_ERROR;
  }

  public BizException(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public BizException(ErrorCode errorCode, Throwable e) {
    super(e);
    this.errorCode = errorCode;
  }

  public BizException(final String errorMsg) {
    super(errorMsg);
    this.errorCode = ErrorCode.SELF_ERROR;
  }

  public BizException(Throwable e) {
    super(e);
    this.errorCode = ErrorCode.DEFAULT;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
