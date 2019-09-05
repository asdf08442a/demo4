package com.enterprise.demo.common.base;

import lombok.Data;

@Data
public class BizResponse<T> {

  private int code;
  private String msg;
  private T data;

  public BizResponse() {
    this.code = ErrorCode.SUCCESS.code;
    this.msg = ErrorCode.SUCCESS.message;
  }


  public static <E> BizResponse<E> build() {
    return build(ErrorCode.SUCCESS.code, ErrorCode.SUCCESS.message, null);
  }

  /**
   * 创建响应体。
   *
   * @param data 响应数据
   * @param <E> 响应数据类型
   * @return 响应体
   */
  public static <E> BizResponse<E> build(E data) {
    return build(ErrorCode.SUCCESS.code, ErrorCode.SUCCESS.message, data);
  }

  /**
   * 创建响应体。
   *
   * @param error 错误
   * @param errorMsg 错误描述
   * @param <E> 响应数据类型
   * @return 响应体
   */
  public static <E> BizResponse<E> build(ErrorCode error, String errorMsg) {
    return build(error.getCode(), errorMsg, null);
  }

  /**
   * 创建响应体。
   *
   * @param error 错误
   * @param <E> 响应数据类型
   * @return 响应体
   */
  public static <E> BizResponse<E> build(ErrorCode error) {
    return build(error.getCode(), error.getMsg(), null);
  }

  /**
   * 创建响应体。
   *
   * @param errorCode 错误代码
   * @param errorMsg 错误描述
   * @param <E> 响应数据类型
   * @return 响应体
   */
  public static <E> BizResponse<E> build(int errorCode, String errorMsg) {
    return build(errorCode, errorMsg, null);
  }


  /**
   * 创建响应体。
   *
   * @param errorCode 错误代码
   * @param errorMsg 错误描述
   * @param data 响应数据
   * @param <E> 响应数据类型
   * @return 响应体
   */
  public static <E> BizResponse<E> build(int errorCode, String errorMsg, E data) {
    BizResponse<E> response = new BizResponse<>();
    response.data = data;
    response.code = errorCode;
    response.msg = errorMsg;
    return response;
  }
}
