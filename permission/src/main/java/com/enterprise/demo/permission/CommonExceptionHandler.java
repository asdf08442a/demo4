package com.enterprise.demo.permission;

import com.enterprise.demo.common.dto.ResponseDTO;
import com.enterprise.demo.common.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
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

  @ExceptionHandler(value = UnknownAccountException.class)
  @ResponseBody
  public ResponseDTO unknownAccountException(Exception e) {
    log.info("无此用户，{}", e);
    return ResultUtils.error("无此用户");
  }

  @ExceptionHandler(value = LockedAccountException.class)
  @ResponseBody
  public ResponseDTO lockedAccountException(Exception e) {
    log.info("用户已无效，{}", e);
    return ResultUtils.error("用户已无效");
  }

  /**
   * 判断错误是否是已定义的已知错误，不是则由未知错误代替，同时记录在log中
   */
  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public ResponseDTO exception(Exception e) {
    log.error("【系统异常】", e);
    if (e instanceof BizException) {
      BizException bizException = (BizException) e;
      return ResultUtils.error(bizException.getMessage());
    }

    return ResultUtils.error();
  }
}
