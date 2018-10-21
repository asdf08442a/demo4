package com.enterprise.demo.sys.common;

import com.enterprise.demo.sys.common.exception.BizException;
import com.enterprise.demo.sys.common.util.ResultUtils;
import com.enterprise.demo.sys.dto.base.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 **/
@Slf4j
@Component
@ControllerAdvice
public class CommonExceptionHandler {

  /**
   * 判断错误是否是已定义的已知错误，不是则由未知错误代替，同时记录在log中
   */
  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public ResponseDTO exceptionGet(Exception e) {
    log.error("【系统异常】{}", e);
    if (e instanceof BizException) {
      BizException bizException = (BizException) e;
      return ResultUtils.error(bizException.getMessage());
    }

    return ResultUtils.error();
  }
}
