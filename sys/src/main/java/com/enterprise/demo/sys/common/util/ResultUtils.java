package com.enterprise.demo.sys.common.util;

import com.enterprise.demo.sys.common.CoreConst;
import com.enterprise.demo.sys.dto.base.PageResultDTO;
import com.enterprise.demo.sys.dto.base.ResponseDTO;
import java.util.List;

/**
 * 返回工具类
 */
public class ResultUtils {

  public static ResponseDTO success() {
    return dto(CoreConst.SUCCESS_CODE, null, null);
  }

  public static ResponseDTO success(String msg) {
    return dto(CoreConst.SUCCESS_CODE, msg, null);
  }

  public static ResponseDTO success(String msg, Object data) {
    return dto(CoreConst.SUCCESS_CODE, msg, data);
  }

  public static ResponseDTO error() {
    return dto(CoreConst.FAIL_CODE, CoreConst.INTERNAL_ERROR, null);
  }

  public static ResponseDTO error(String msg) {
    return dto(CoreConst.FAIL_CODE, msg, null);
  }

  public static ResponseDTO error(String msg, Object data) {
    return dto(CoreConst.FAIL_CODE, msg, data);
  }

  public static PageResultDTO table(List<?> list, Long total) {
    return new PageResultDTO(list, total);
  }

  public static ResponseDTO dto(Integer status, String message, Object data) {
    return new ResponseDTO<>(status, message, data);
  }
}
