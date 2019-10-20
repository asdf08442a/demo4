package com.enterprise.demo.core.service;

import com.enterprise.demo.common.base.BizException;
import com.enterprise.demo.common.base.ErrorCode;
import com.enterprise.demo.common.redis.RedisLock;
import com.enterprise.demo.core.service.base.UserService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginBizService {

  @Resource
  private UserService userService;

  public Boolean login(String userId) {
    if (!RedisLock.lock(userId)) {
      throw new BizException(ErrorCode.LOCK_FAIL);
    }
    log.info("=====biz doing");
    try {
      return true;
    } finally {
      RedisLock.unlock(userId);
    }
  }
}
