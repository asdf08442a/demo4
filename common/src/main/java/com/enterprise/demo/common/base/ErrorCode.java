package com.enterprise.demo.common.base;

/**
 * 通用api返回值枚举
 */
public enum ErrorCode {

  /**
   * 成功
   */
  SUCCESS(0, "ok"),
  /**
   * 非法请求
   */
  BAD_REQUEST(400, "bad request"),
  /**
   * 非法用户
   */
  USER_NOT_FOUND(401, "该用户无法认证"),
  /**
   * 未授权
   */
  FORBIDDEN(403, "没有授权"),
  /**
   * 服务不存在
   */
  NOT_FOUND(404, "服务不存在"),
  /**
   * 请求方法不支持
   */
  METHOD_NOT_ALLOWED(405, "请求方法不支持"),
  /**
   * 第三方请求超时
   */
  REQUEST_TIMEOUT(408, "第三方请求超时"),
  /**
   * 媒体类型不支持
   */
  UNSUPPORTED_MEDIA_TYPE(415, "媒体类型不支持"),
  /**
   * 请求参数非法
   */
  INVALID_PARAMETER(412, "请求参数非法"),
  /**
   * 请求参数反序列化失败
   */
  PARAMETER_DESERIALIZE_FAIL(413, "请求参数反序列化失败"),
  /**
   * 第三方响应非法
   */
  INVALID_RESPONSE(420, "第三方响应非法"),
  /**
   * 第三方响应反序列化失败
   */
  RESPONSE_DESERIALIZE_FAIL(421, "第三方响应反序列化失败"),
  /**
   * 请求次数过多
   */
  TOO_MANY_REQUESTS(429, "请求次数过多"),
  /**
   * token非法
   */
  INVALID_TOKEN(498, "token非法"),
  /**
   * 服务器内部错误,默认错误
   */
  DEFAULT(500, " 服务器内部错误"),
  /**
   * 业务错误
   */
  BIZ_ERROR(501, " 业务错误");

  public final int code;

  public final String message;

  ErrorCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  /**
   * return enum based on code integer.
   *
   * @param code code integer to search enum.
   * @return mapped enum
   */
  public static ErrorCode codeOf(int code) {
    for (ErrorCode errorCode : values()) {
      if (errorCode.code == code) {
        return errorCode;
      }
    }
    return DEFAULT;
  }

  public int getCode() {
    return code;
  }

  public String getMsg() {
    return message;
  }
}
