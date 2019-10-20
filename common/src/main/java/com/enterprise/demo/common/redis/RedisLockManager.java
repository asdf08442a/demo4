package com.enterprise.demo.common.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;

public class RedisLockManager {

  private static Config config = new Config();
  private static RedissonClient redisson;

  private static String host;
  private static String port;

  @Value("${spring.redis.host}")
  public static void setHost(String host) {
    RedisLockManager.host = host;
  }

  @Value("${spring.redis.port}")
  public static void setPort(String port) {
    RedisLockManager.port = port;
  }

  static {
    config.useSingleServer().setAddress(host + ":" + port);
    redisson = Redisson.create(config);
  }

  public static RedissonClient getRedisson() {
    return redisson;
  }
}
