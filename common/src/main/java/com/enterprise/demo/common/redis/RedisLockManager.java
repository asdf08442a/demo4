package com.enterprise.demo.common.redis;

import javax.annotation.PostConstruct;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisLockManager {

  private static RedissonClient redisson;

  private static String host;
  private static String port;

  @Value("${spring.redis.host}")
  public void setHost(String host) {
    RedisLockManager.host = host;
  }

  @Value("${spring.redis.port}")
  public void setPort(String port) {
    RedisLockManager.port = port;
  }

  @PostConstruct
  public void init() {
    Config config = new Config();
    config.useSingleServer().setAddress("redis://" + host + ":" + port);
    redisson = Redisson.create(config);
  }

  public static RedissonClient getRedisson() {
    return redisson;
  }
}
