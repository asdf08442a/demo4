package com.enterprise.demo.sys.common;

import java.util.Arrays;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * http切面
 **/
@Slf4j
@Aspect
@Component
public class HttpAspect {

  private ThreadLocal<Long> startTime = new ThreadLocal<>();

  @Pointcut("execution(public * com.enterprise.demo.sys.controller.*.*(..))")
  public void log() {

  }

  @Before("log()")
  public void doBefore(JoinPoint joinPoint) {
    startTime.set(System.currentTimeMillis());

    MDC.put("requestId", UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());

    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();

    // 记录下请求内容
    log.info("URL : {}", request.getRequestURL().toString());
    log.info("HTTP_METHOD : {}", request.getMethod());
    log.info("IP : {}", request.getRemoteAddr());
    log.info("CLASS_METHOD : {}",
        joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    log.info("ARGS : {}", Arrays.toString(joinPoint.getArgs()));
  }

  @AfterReturning(pointcut = "log()", returning = "object")
  public void doAfterReturing(Object object) {
    // 处理完请求，返回内容
    log.info("RESPONSE : {}", object);
    log.info("SPEND TIME : {} ms", (System.currentTimeMillis() - startTime.get()));
    MDC.clear();
  }
}
