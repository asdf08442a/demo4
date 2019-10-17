package com.enterprise.demo.common;

import com.enterprise.demo.common.base.BizException;
import com.enterprise.demo.common.base.BizResponse;
import com.enterprise.demo.common.base.ErrorCode;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice(basePackages = "com.enterprise.demo.portal.controller")
@Slf4j
public class ResponseControllerAdvice {

  @ExceptionHandler(BizException.class)
  @ResponseBody
  public BizResponse handleAutoFastFailException(BizException e,
      HttpServletRequest request) {
    log.error("Exception is:", e);
    return BizResponse.build(ErrorCode.BIZ_ERROR, e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public BizResponse handleAutoFastFailException(Exception e,
      HttpServletRequest request) {
    log.error("Exception is:", e);
    return BizResponse.build(ErrorCode.DEFAULT, e.getMessage());
  }

  @ExceptionHandler({BindException.class, ConstraintViolationException.class})
  public BizResponse validatorExceptionHandler(Exception e) {
    log.error("Exception is:", e);
    String msg = e instanceof BindException ? msgConverter(((BindException) e).getBindingResult())
        : msgConverter(((ConstraintViolationException) e).getConstraintViolations());
    return BizResponse.build(ErrorCode.INVALID_PARAMETER, msg);
  }

  /**
   * 校验消息转换拼接 BindingResult
   */
  private static String msgConverter(BindingResult bindingResult) {
    List<FieldError> fieldErrors = bindingResult.getFieldErrors();
    return fieldErrors.stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.joining(","))
        .toLowerCase();
  }

  /**
   * 校验消息转换拼接 ConstraintViolation
   */
  private String msgConverter(Set<ConstraintViolation<?>> constraintViolations) {
    return constraintViolations.stream()
        .map(ConstraintViolation::getMessage)
        .collect(Collectors.joining(","))
        .toLowerCase();
  }
}
