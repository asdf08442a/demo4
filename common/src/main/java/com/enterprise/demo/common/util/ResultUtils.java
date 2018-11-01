package com.enterprise.demo.common.util;

import com.enterprise.demo.common.CommonConstant;
import com.enterprise.demo.common.dto.ResponseDTO;

/**
 * 返回工具类
 */
public class ResultUtils {

  public static ResponseDTO success() {
    return dto(CommonConstant.SUCCESS_CODE, null, null);
  }

  public static ResponseDTO success(String msg) {
    return dto(CommonConstant.SUCCESS_CODE, msg, null);
  }

  public static ResponseDTO success(Object obj) {
    return dto(CommonConstant.SUCCESS_CODE, CommonConstant.SUCCESS_CODE_MSG, obj);
  }

  public static ResponseDTO success(String msg, Object data) {
    return dto(CommonConstant.SUCCESS_CODE, msg, data);
  }

  public static ResponseDTO error() {
    return dto(CommonConstant.FAIL_CODE, CommonConstant.FAIL_CODE_MSG, null);
  }

  public static ResponseDTO error(String msg) {
    return dto(CommonConstant.FAIL_CODE, msg, null);
  }

  public static ResponseDTO error(String msg, Object data) {
    return dto(CommonConstant.FAIL_CODE, msg, data);
  }

  public static ResponseDTO dto(Integer status, String message, Object data) {
    return new ResponseDTO<>(status, message, data);
  }
}
